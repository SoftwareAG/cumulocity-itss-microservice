package itss.service;

import c8y.IsDevice;
import c8y.Position;
import com.cumulocity.model.ID;
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
import itss.type.ITSS_SensorValue;
import itss.type.svenson.Address;
import itss.type.svenson.SensorValue;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class C8YClient {

    private static final Logger logger = LoggerFactory.getLogger(ITSSService.class);

    @Autowired
    private InventoryApi inventoryApi;

    @Autowired
    private IdentityApi identityApi;

    @Autowired
    private EventApi eventApi;

    @Autowired
    private MeasurementApi measurementApi;

    private static final String SERIAL = "c8y_Serial";

    public ExternalIDRepresentation findExternalId(String externalId, String type) {
        ID id = new ID();
        if(type != null)
            id.setType(type);
        else
            id.setType(SERIAL);
        id.setValue(externalId);
        ExternalIDRepresentation extId = null;
        try {
            extId = identityApi.getExternalId(id);
        } catch (SDKException e) {
            logger.info("External ID {} not found", externalId);
        }
        return extId;
    }

    public MeasurementRepresentation createMeasurement(String name, String type, ManagedObjectRepresentation mor, DateTime dateTime, HashMap<String, MeasurementValue> mvMap) {
        try {
            MeasurementRepresentation measurementRepresentation = new MeasurementRepresentation();
            measurementRepresentation.set(mvMap, name);
            measurementRepresentation.setType(type);
            measurementRepresentation.setSource(mor);
            measurementRepresentation.setDateTime(dateTime);
            logger.debug("Creating Measurement {}", measurementRepresentation);
            return measurementApi.create(measurementRepresentation);
        } catch (SDKException e) {
            logger.error("Error creating Measurement", e);
            return null;
        }
    }

    public EventRepresentation createPositionEvent(Position position, ManagedObjectRepresentation mor, DateTime dateTime) {
        EventRepresentation eventRepresentation = new EventRepresentation();
        eventRepresentation.setSource(mor);
        eventRepresentation.setDateTime(dateTime);
        eventRepresentation.set(position);
        eventRepresentation.setText("Location Update Event");
        eventRepresentation.setType("c8y_LocationUpdate");
        logger.debug("Creating Event {}", eventRepresentation);
        return eventApi.create(eventRepresentation);
    }

    public EventRepresentation createPositionEvent(Position position, ManagedObjectRepresentation mor, long utcTimestamp) {
        DateTime dt = new DateTime(utcTimestamp);
        return createPositionEvent(position, mor, dt);
    }
}
