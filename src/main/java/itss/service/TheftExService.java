package itss.service;

import c8y.IsDevice;
import c8y.Position;
import com.cumulocity.rest.representation.identity.ExternalIDRepresentation;
import com.cumulocity.rest.representation.inventory.ManagedObjectRepresentation;
import com.cumulocity.sdk.client.SDKException;
import com.cumulocity.sdk.client.identity.IdentityApi;
import com.cumulocity.sdk.client.inventory.InventoryApi;
import itss.type.theftex.Device;
import itss.type.theftex.DeviceResponse;
import itss.type.theftex.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class TheftExService {

    private static final Logger logger = LoggerFactory.getLogger(TheftExService.class);

    private static final String theftExURL = "https://middleware.theftex.com/api/pub/v1/devices";

    private static final String theftExAPIKey = "uC2l90HoN6QkMxiXGSk9C9D6FCICPZxYCo33H5kP";

    @Autowired
    C8YClient c8YClient;

    @Autowired
    InventoryApi inventoryApi;

    @Autowired
    IdentityApi identityApi;


    @Scheduled(cron = "0 0 */1 * * *")
    public void fetchDevices() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", theftExAPIKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<DeviceResponse> responseEntity = restTemplate.exchange(theftExURL, HttpMethod.GET, requestEntity, DeviceResponse.class);
            logger.info("Polling of Devices from TheftEx successful with result {}", responseEntity.getBody().getMetaData().getStatusCode());
            DeviceResponse response = responseEntity.getBody();
            if (response.getData() != null) {
                for (Device device : response.getData()) {
                    upsertTheftExDevice(device, response.getError());

                }
            }

        } catch (Exception e) {
            logger.error("Polling of Devices from TheftEx failed with result {}", e);
        }
    }

    public ManagedObjectRepresentation upsertTheftExDevice(Device device, String error) {
        try {
            String qrCode = device.getQrCode();
            String name = device.getLabel();
            Location location = device.getLocation();
            if (qrCode != null) {
                logger.info("Upsert device with name {} and id {}", name, qrCode);
                ExternalIDRepresentation extId = c8YClient.findExternalId(qrCode, null);
                ManagedObjectRepresentation mor;
                boolean deviceExists = true;
                if (extId == null) {
                    mor = new ManagedObjectRepresentation();
                    mor.setType("c8y_theftExDevice");
                    mor.setName(name);
                    deviceExists = false;
                } else {
                    mor = extId.getManagedObject();
                }
                mor.set(new IsDevice());
                mor.setName(name);
                if (error != null) {
                    mor.set(error, "theftEx_error");
                }
                if (location != null) {
                    Position pos = new Position();
                    pos.setLng(new BigDecimal(location.getLng()));
                    pos.setLat(new BigDecimal(location.getLat()));
                    mor.set(pos);
                    c8YClient.createPositionEvent(pos, mor, location.getUpdatedAt());
                }

                if (!deviceExists) {
                    mor = inventoryApi.create(mor);
                    extId = new ExternalIDRepresentation();
                    extId.setExternalId(qrCode);
                    extId.setType("c8y_Serial");
                    extId.setManagedObject(mor);
                    identityApi.create(extId);
                } else
                    mor = inventoryApi.update(mor);
                return mor;
            } else
                return null;
        } catch (SDKException ex) {
            logger.info("Error on creating DT Device", ex);
            return null;
        }
    }
}
