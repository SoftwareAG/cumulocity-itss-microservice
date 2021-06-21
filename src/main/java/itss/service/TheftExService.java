package itss.service;

import c8y.IsDevice;
import c8y.Position;

import com.cumulocity.microservice.subscription.service.MicroserviceSubscriptionsService;
import com.cumulocity.rest.representation.identity.ExternalIDRepresentation;
import com.cumulocity.rest.representation.inventory.ManagedObjectRepresentation;
import com.cumulocity.rest.representation.measurement.MeasurementCollectionRepresentation;
import com.cumulocity.rest.representation.measurement.MeasurementRepresentation;
import com.cumulocity.model.measurement.MeasurementValue;
import com.cumulocity.sdk.client.SDKException;
import com.cumulocity.sdk.client.identity.IdentityApi;
import com.cumulocity.sdk.client.inventory.InventoryApi;
import com.cumulocity.sdk.client.measurement.MeasurementApi;
import itss.type.theftex.Device;
import itss.type.theftex.DeviceResponse;
import itss.type.theftex.LocationValue;
import itss.type.theftex.SensorData;
import itss.type.theftex.SensorDataResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TheftExService {

    private static final Logger logger = LoggerFactory.getLogger(TheftExService.class);
    
    private static final String theftExBaseURL = "https://portal.theftex.com/api/v1/";

    private static final String theftExAPIKey = "uZbiU7QqjocH5zmn4IZ3nXdv9dDuPlBMxNHtxFz4";
    
    private static final String theftExSensorDataTimeStampFragment = "theftEx_LastSensorDataTimestamp";

    @Autowired
    C8YClient c8YClient;

    @Autowired
    InventoryApi inventoryApi;

    @Autowired
    IdentityApi identityApi;
    
    @Autowired
    MeasurementApi measurementApi;
    
    @Autowired
    MicroserviceSubscriptionsService microserviceSubscriptionsService;


    @Scheduled(cron = "0 0 */1 * * *")
    public void fetchDevices() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-ApiKey", theftExAPIKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<DeviceResponse> responseEntity = restTemplate.exchange(theftExBaseURL + "devices?size=2000", HttpMethod.GET, requestEntity, DeviceResponse.class);
            logger.info("Polling of Devices from TheftEx successful with result {}", responseEntity.getStatusCode());
            DeviceResponse response = responseEntity.getBody();
            if (response.getContent() != null) {
                for (Device device : response.getContent()) {
                	List<SensorData> lastDeviceLocations = getLastLocationOfDevice(device.getId());
                	microserviceSubscriptionsService.runForEachTenant(() ->  {
                		upsertTheftExDevice(device, lastDeviceLocations);
                	});
                }
            }
        } catch (Exception e) {
            logger.error("Polling of Devices from TheftEx failed with result {}", e);
        }
    }
    
    private List<SensorData> getLastLocationOfDevice(String deviceId) {
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-ApiKey", theftExAPIKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity<>(headers);
        String url = theftExBaseURL + "devicehistory/" + deviceId + "/sensordata?size=2000";
        try {
        	ResponseEntity<SensorDataResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, SensorDataResponse.class);
        	logger.info("Polling of Device Locations from TheftEx successful with result {}", responseEntity.getStatusCode());
        	SensorDataResponse response = responseEntity.getBody();
            if (response.getContent() != null) {
            	return response.getContent();
            }
        } catch (Exception e) {
            logger.error("Polling of Device Locations from TheftEx failed with result {}", e);
        }
        return new ArrayList<SensorData>();
        
    }

    public ManagedObjectRepresentation upsertTheftExDevice(Device device, List<SensorData> lastSensorData) {
        try {
            String qrCode = device.getQrCode();
            String name = device.getLabel();
            if (qrCode != null) {
                logger.info("Upsert device with name {} and id {}", name, qrCode);
                ExternalIDRepresentation extId = c8YClient.findExternalId(qrCode, null);
                ManagedObjectRepresentation mor;
                if (extId == null) {
                    mor = new ManagedObjectRepresentation();
                    mor.setType("c8y_theftExDevice");
                    mor.setName(name);
                    mor.set(new IsDevice());
                    mor = inventoryApi.create(mor);
                    extId = new ExternalIDRepresentation();
                    extId.setExternalId(qrCode);
                    extId.setType("c8y_Serial");
                    extId.setManagedObject(mor);
                    identityApi.create(extId);
                } else {
                    mor = inventoryApi.get(extId.getManagedObject().getId());
                }
                
            	String lastSensorDataTimestamp = (String) mor.getProperty(theftExSensorDataTimeStampFragment);
            	DateTime previousSensorDataTimeStamp = null;
            	if (lastSensorDataTimestamp != null) {
            		previousSensorDataTimeStamp = new DateTime(lastSensorDataTimestamp);
            	}
            	DateTime newSensorDataTimeStamp = null;
            	int locationEventsCreated = 0;
            	
            	List<MeasurementRepresentation> measurementList = new ArrayList<MeasurementRepresentation>();
                for (SensorData sensorData : lastSensorData) {
                	DateTime timestampOfData = new DateTime(sensorData.getTimestamp());
                	if (previousSensorDataTimeStamp != null && previousSensorDataTimeStamp.getMillis() >= timestampOfData.getMillis()) {
                		break;
                	}
                	if (newSensorDataTimeStamp == null) {
                		newSensorDataTimeStamp = timestampOfData;
                		ManagedObjectRepresentation moUpdate = new ManagedObjectRepresentation();
                		moUpdate.set(sensorData.getTimestamp(), theftExSensorDataTimeStampFragment);
                		moUpdate.setId(mor.getId());
                		mor = inventoryApi.update(moUpdate);
                	}
                	LocationValue locValue = sensorData.getLocation();
                	if (locValue != null) {
                		Position pos = new Position();
                        pos.setLng(locValue.getLng());
                        pos.setLat(locValue.getLat());
                        c8YClient.createPositionEvent(pos, mor, timestampOfData);
                        locationEventsCreated++;
                		if (timestampOfData.getMillis() == newSensorDataTimeStamp.getMillis()) {
	                		ManagedObjectRepresentation moUpdate = new ManagedObjectRepresentation();
	                        moUpdate.setId(mor.getId());
	                        moUpdate.set(pos);
	                        mor = inventoryApi.update(moUpdate);
                		}
                	}
                	
                	Integer temperature = sensorData.getTemperature();
                	Integer light = sensorData.getLight();
                	if (temperature != null || light != null) {
                		MeasurementRepresentation measurement = new MeasurementRepresentation();
                		measurement.setSource(mor);
                		measurement.setDateTime(timestampOfData);
                		measurement.setType("c8y_theftEx");
                		
                		
                		if (temperature != null) {
                			Map<String, MeasurementValue> temperatureMap = new HashMap<String, MeasurementValue>();
                			MeasurementValue temperatureValue = new MeasurementValue(new BigDecimal(temperature), "C");
                			temperatureMap.put("T", temperatureValue);
                			
                			measurement.setProperty("c8y_TemperatureMeasurement", temperatureMap);
                		}
                		
                		if (light != null) {
                			Map<String, MeasurementValue> lightMap = new HashMap<String, MeasurementValue>();
                			MeasurementValue lightValue = new MeasurementValue(new BigDecimal(light), "lux");
                			lightMap.put("e", lightValue);
                			
                			measurement.setProperty("c8y_LightMeasurement", lightMap);
                		}
                		
                		
                		measurementList.add(measurement);
                	}
                	
                	
                }
                MeasurementCollectionRepresentation measurementCollection = new MeasurementCollectionRepresentation();
            	measurementCollection.setMeasurements(measurementList);
            	measurementApi.createBulkWithoutResponse(measurementCollection);
            	logger.info("Created {} LocationUpdateEvents and {} Measurements for Device {}", locationEventsCreated, measurementList.size(), mor.getId());
                return mor;
            } else
                return null;
        } catch (SDKException ex) {
            logger.info("Error on creating DT Device", ex);
            return null;
        }
    }
}
