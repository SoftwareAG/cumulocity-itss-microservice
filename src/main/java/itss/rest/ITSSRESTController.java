package itss.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import itss.service.ITSSService;
import itss.type.AssembledNotification;
import itss.type.ITSS_SensorValue;
import itss.type.ITSS_SensorValues;
import itss.type.Shock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("1.2")
public class ITSSRESTController {
    final Logger logger = LoggerFactory.getLogger(ITSSRESTController.class);

    @Autowired
    ITSSService itssService;

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/assembledNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity assembledNotification(@RequestBody String assembledNotificationString, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        logger.info("Assembled Notification received {}", assembledNotificationString);
        try {
            AssembledNotification assembledNotification = mapper.readValue(assembledNotificationString, AssembledNotification.class);
            CompletableFuture.runAsync(() -> {
                itssService.handleAssembledNotification(assembledNotification);
            });
        } catch (Exception ex) {
            logger.error("Error during processing of assembled Notification {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/shockDetected", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity shockDetected(@RequestBody String shockString, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        logger.info("Shock Notification received {}", shockString);
        try {
            Shock shock = mapper.readValue(shockString, Shock.class);
            CompletableFuture.runAsync(() -> {
                itssService.handleShockNotification(shock);
            });
        } catch (Exception ex) {
            logger.error("Error during processing of assembled Notification {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/sensorValues", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sensorValues(@RequestBody String sensorValuesString, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        logger.info("Sensor Values Notification received {}", sensorValuesString);
        try {
            ITSS_SensorValues  itss_sensorValues = mapper.readValue(sensorValuesString, ITSS_SensorValues.class);
            CompletableFuture.runAsync(() -> {
                itssService.handleSensorValues(itss_sensorValues);
            });
        } catch (Exception ex) {
            logger.error("Error during processing of assembled Notification {}");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}
