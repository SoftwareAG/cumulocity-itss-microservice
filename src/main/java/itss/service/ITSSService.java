package itss.service;

import c8y.IsDevice;
import c8y.Position;
import com.cumulocity.microservice.subscription.service.MicroserviceSubscriptionsService;
import com.cumulocity.model.measurement.MeasurementValue;
import com.cumulocity.rest.representation.event.EventRepresentation;
import com.cumulocity.rest.representation.identity.ExternalIDRepresentation;
import com.cumulocity.rest.representation.inventory.ManagedObjectRepresentation;
import com.cumulocity.rest.representation.measurement.MeasurementRepresentation;
import com.cumulocity.sdk.client.SDKException;
import com.cumulocity.sdk.client.event.EventApi;
import com.cumulocity.sdk.client.identity.IdentityApi;
import com.cumulocity.sdk.client.inventory.InventoryApi;
import com.cumulocity.sdk.client.measurement.MeasurementApi;
import itss.type.*;
import itss.type.svenson.Address;
import itss.type.svenson.SensorValue;
import itss.utils.Utils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;


@Service
public class ITSSService {

    @Autowired
    private MicroserviceSubscriptionsService subscriptionsService;

    @Autowired
    private InventoryApi inventoryApi;

    @Autowired
    private IdentityApi identityApi;

    @Autowired
    private EventApi eventApi;

    @Autowired
    private MeasurementApi measurementApi;

    @Autowired
    private C8YClient c8YClient;

    private static final Logger logger = LoggerFactory.getLogger(ITSSService.class);

    private static final String SERIAL = "c8y_Serial";

    public void handleSensorValues(ITSS_SensorValues itss_sensorValues) {
        subscriptionsService.runForEachTenant(() -> {
            String deviceId = itss_sensorValues.getItssTelematicsDeviceId();
            String transportId = itss_sensorValues.getItssTransportDeviceId();
            List<ITSS_SensorValue> sensorValueList = itss_sensorValues.getItssSensorValueList();
            ManagedObjectRepresentation mor = upsertITSSDevice(deviceId, deviceId, null, null,null, null, null, null, null, null, null, null, null, sensorValueList);
            createSensorMeasurements(sensorValueList, mor);
        });
    }

    public void handleShockNotification(Shock shock) {
        subscriptionsService.runForEachTenant(() -> {
            String deviceId = shock.getItssTelematicsDeviceId();
            String transportId = shock.getItssTransportDeviceId();
            BigDecimal xAxis = shock.getxAxis();
            BigDecimal yAxis = shock.getyAxis();
            BigDecimal zAxis = shock.getzAxis();
            Boolean xAxisTriggered = shock.isxAxisTriggered();
            Boolean yAxisTriggered = shock.isyAxisTriggered();
            Boolean zAxisTriggered = shock.iszAxisTriggered();
            GNSS_Position position = shock.getGnssPosition();
            Position pos = null;
            Address address = null;
            if(position != null) {
                pos = new Position();
                pos.setLat(position.getGnssLatitude());
                pos.setLng(position.getGnssLongitude());
                pos.setAlt(position.getGnssAltitude());
                pos.setAccuracy(pos.getAccuracy());
                ITSS_LocationInfo locationInfo = position.getLocationInfo();
                if(locationInfo != null)  {
                    address = new Address();
                    address.setCity(locationInfo.getLocationCity());
                    address.setCityCode(locationInfo.getLocationZip());
                    address.setCountry(locationInfo.getLocationCountry());
                    address.setStreet(locationInfo.getLocationStreet());
                    address.setTerritory(locationInfo.getLocationGeoZone());
                }
            }

            long utcTimestamp = shock.getTimestamp();
            ManagedObjectRepresentation mor = upsertITSSDevice(deviceId, deviceId, pos, null,null, utcTimestamp, null, xAxis, yAxis, zAxis, null, null, address, null);
            c8YClient.createPositionEvent(pos, mor, utcTimestamp*1000);
            if (xAxisTriggered || yAxisTriggered || zAxisTriggered) {
                createShockEvent(mor, utcTimestamp, xAxisTriggered, yAxisTriggered, zAxisTriggered, xAxis, yAxis, zAxis);
                createShockMeasurement(mor,shock.getxAxis(), shock.getyAxis(), shock.getzAxis(), utcTimestamp);
            }
        });
    }

    public void handleAssembledNotification(AssembledNotification assembledNotification) {
        subscriptionsService.runForEachTenant(() -> {
            try {
                String deviceId = assembledNotification.getItssTelematicsDeviceId();
                GNSS_Position position = assembledNotification.getGnssPosition();
                Position pos = null;
                BigDecimal speed = null;
                Address address = null;
                if (position != null) {
                    pos = new Position();
                    pos.setLat(position.getGnssLatitude());
                    pos.setLng(position.getGnssLongitude());
                    pos.setAlt(position.getGnssAltitude());
                    pos.setAccuracy(pos.getAccuracy());
                    speed = position.getGnssSpeedKmph();
                    ITSS_LocationInfo locationInfo = position.getLocationInfo();
                    if (locationInfo != null) {
                        address = new Address();
                        address.setCity(locationInfo.getLocationCity());
                        address.setCityCode(locationInfo.getLocationZip());
                        address.setCountry(locationInfo.getLocationCountry());
                        address.setStreet(locationInfo.getLocationStreet());
                        address.setTerritory(locationInfo.getLocationGeoZone());
                    }
                }

                List<ITSS_SensorValue> sensorValueList = assembledNotification.getItssSensorValueList();
                String loadingState = assembledNotification.getLoadingState();
                long mileage = assembledNotification.getMileage();
                String movementState = assembledNotification.getMovementState();
                long payload = assembledNotification.getPayload();
                long utcTimestamp = assembledNotification.getUtcTimestamp()*1000;
                BigDecimal xAxis = assembledNotification.getxAxis();
                BigDecimal yAxis = assembledNotification.getyAxis();
                BigDecimal zAxis = assembledNotification.getzAxis();
                Boolean xAxisTriggered = assembledNotification.isxAxisTriggered();
                Boolean yAxisTriggered = assembledNotification.isyAxisTriggered();
                Boolean zAxisTriggered = assembledNotification.iszAxisTriggered();
                ManagedObjectRepresentation mor = upsertITSSDevice(deviceId, deviceId, pos, speed, movementState, utcTimestamp, mileage, xAxis, yAxis, zAxis, loadingState, payload, address, sensorValueList);
                createSensorMeasurements(sensorValueList, mor);
                if(pos != null && position != null)
                    c8YClient.createPositionEvent(pos, mor, position.getGnssUTCtimestamp()*1000);
                if (xAxisTriggered || yAxisTriggered || zAxisTriggered) {
                    createShockEvent(mor, utcTimestamp, xAxisTriggered, yAxisTriggered, zAxisTriggered, xAxis, yAxis, zAxis);
                    createShockMeasurement(mor, xAxis, yAxis, zAxis, utcTimestamp);
                }
            } catch (Exception e) {
                logger.error("Error in Handling Assembled Notification {}", e);
            }
        });
    }

    public EventRepresentation createShockEvent(ManagedObjectRepresentation mor, long utcTimestamp, Boolean xAxisTriggered, Boolean yAxisTriggered, Boolean zAxisTriggered, BigDecimal xAxis, BigDecimal yAxis, BigDecimal zAxis) {
        EventRepresentation eventRepresentation = new EventRepresentation();
        eventRepresentation.setSource(mor);
        DateTime dt = new DateTime(utcTimestamp);
        eventRepresentation.setDateTime(dt);
        eventRepresentation.set(xAxisTriggered, "itss_XAxisTriggered");
        eventRepresentation.set(yAxisTriggered, "itss_YAxisTriggered");
        eventRepresentation.set(zAxisTriggered, "itss_ZAxisTriggered");
        eventRepresentation.set(xAxis, "itss_XAxis");
        eventRepresentation.set(yAxis, "itss_YAxis");
        eventRepresentation.set(zAxis, "itss_ZAxis");
        eventRepresentation.setText("Shock Detection Event");
        eventRepresentation.setType("c8y_ShockEvent");
        logger.debug("Creating Event {}", eventRepresentation);
        return eventApi.create(eventRepresentation);
    }

    public MeasurementRepresentation createShockMeasurement(ManagedObjectRepresentation mor, BigDecimal xValue, BigDecimal yValue, BigDecimal zValue, long utcTimestamp) {
        DateTime dateTime = new DateTime(utcTimestamp);
        xValue = xValue.divide(BigDecimal.valueOf(1000));
        yValue = yValue.divide(BigDecimal.valueOf(1000));
        zValue = zValue.divide(BigDecimal.valueOf(1000));
        String sensorId = "Shock";
        String sensorTypeX = "shockX";
        String sensorTypeY = "shockY";
        String sensorTypeZ = "shockZ";
        HashMap<String, MeasurementValue> mvMap = new HashMap<>();
        MeasurementValue mvx = new MeasurementValue();
        mvx.setValue(xValue);
        mvx.setUnit("g");
        mvMap.put(sensorTypeX, mvx);
        MeasurementValue mvy = new MeasurementValue();
        mvy.setValue(yValue);
        mvy.setUnit("g");
        mvMap.put(sensorTypeY, mvy);
        MeasurementValue mvz = new MeasurementValue();
        mvz.setValue(zValue);
        mvz.setUnit("g");
        mvMap.put(sensorTypeZ, mvz);
        return c8YClient.createMeasurement(sensorId, "c8y_Shock", mor, dateTime, mvMap);
    }

    public void createSensorMeasurements(List<ITSS_SensorValue> sensorValueList, ManagedObjectRepresentation mor) {
        if(sensorValueList != null) {
            for (ITSS_SensorValue sensorValue : sensorValueList) {
                DateTime dateTime = new DateTime(sensorValue.getSamplingUTCTimestamp()*1000);
                BigDecimal value = sensorValue.getValue();
                String sensorId = sensorValue.getItssSensorId();
                String sensorType = sensorValue.getItssSensorType() != null ? sensorValue.getItssSensorType() : "c8y_Sensor";
                HashMap<String, MeasurementValue> mvMap = new HashMap<>();
                MeasurementValue mv = new MeasurementValue();
                if("DeviceTemp".equals(sensorId)) {
                    value = value.subtract(BigDecimal.valueOf(273.15)).setScale(2, RoundingMode.HALF_UP);
                    mv.setUnit("°C");
                }
                mv.setValue(value);

                mvMap.put(sensorType, mv);
                c8YClient.createMeasurement(sensorId, sensorType, mor, dateTime, mvMap);
            }
        }
    }

    private ManagedObjectRepresentation upsertITSSDevice(String name, String id, Position position, BigDecimal speed, String movementState, Long utcTimestamp, Long mileage, BigDecimal xAxis, BigDecimal yAxis, BigDecimal zAxis, String loadingState, Long payload, Address  address, List<ITSS_SensorValue> sensorValueList) {
        try {
            logger.info("Upsert device with name {} and id {}", name, id);
            ExternalIDRepresentation extId = c8YClient.findExternalId(id, SERIAL);
            ManagedObjectRepresentation mor;
            boolean deviceExists = true;
            if (extId == null) {
                mor = new ManagedObjectRepresentation();
                mor.setType("c8y_ITSSTracker");
                mor.setName(name);
                deviceExists = false;
            } else {
                mor = extId.getManagedObject();
            }
            mor.set(new IsDevice());

            if (position != null)
                mor.set(position);
            if(speed != null)
                mor.set(speed, "itss_Speed");
            if(movementState != null)
                mor.set(movementState, "itss_MovementState");

            mor.set(utcTimestamp, "itss_UTCTimestamp");
            if(mileage != null)
                mor.set(mileage, "itss_Mileage");
            if(xAxis != null)
                mor.set(xAxis, "itss_XAxis");
            if(yAxis != null)
                mor.set(yAxis, "itss_YAxis");
            if(zAxis != null)
                mor.set(zAxis, "itss_ZAxis");
            if(loadingState != null)
                mor.set(loadingState, "itss_LoadingState");
            if(payload != null)
                mor.set(payload, "itss_Payload");
            if(address != null)
                mor.set(address, "c8y_Address");

            if(sensorValueList != null) {
                for (ITSS_SensorValue sensorValue : sensorValueList) {
                    SensorValue sensorVal = new SensorValue();
                    sensorVal.setItssSensorId(sensorValue.getItssSensorId());
                    sensorVal.setItssSensorType(sensorValue.getItssSensorType());
                    sensorVal.setValue(sensorValue.getValue());
                    sensorVal.setSamplingUTCTimestamp(sensorValue.getSamplingUTCTimestamp()*1000);
                    if("DeviceTemp".equals(sensorValue.getItssSensorId())) {
                       BigDecimal celsiusValue = sensorValue.getValue().subtract(BigDecimal.valueOf(273.15)).setScale(2, RoundingMode.HALF_UP);
                       sensorVal.setValue(celsiusValue);
                    }
                    mor.set(sensorVal, "itss_Sensor-"+sensorValue.getItssSensorId());
                }
            }

            if (!deviceExists) {
                mor = inventoryApi.create(mor);
                extId = new ExternalIDRepresentation();
                extId.setExternalId(id);
                extId.setType(SERIAL);
                extId.setManagedObject(mor);
                identityApi.create(extId);
            } else
                mor = inventoryApi.update(mor);
            return mor;
        } catch (SDKException e) {
            logger.info("Error on creating DT Device", e);
            return null;
        }
    }


}
