package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeofenceState{
    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "UTCtimestamp")
    long utcTimestamp;
    @JsonProperty(value = "ITSS_GeoFence")
    ITSS_Geofence itssGeofence;
    @JsonProperty(value = "ITSS_GeofenceEventTrigger")
    String itssGeofenceEventTrigger;
    @JsonProperty(value = "GNSS_Position")
    GNSS_Position gnssPosition;
    @JsonProperty(value = "ITSS_TelematicsApplicationID")
    String itssTelematicsApplicationId;

    public String getItssTransportDeviceId() {
        return itssTransportDeviceId;
    }
    public void setItssTransportDeviceId(String itssTransportDeviceId) {
        this.itssTransportDeviceId = itssTransportDeviceId;
    }
    public String getItssTelematicsDeviceId() {
        return itssTelematicsDeviceId;
    }
    public void setItssTelematicsDeviceId(String itssTelematicsDeviceId) {
        this.itssTelematicsDeviceId = itssTelematicsDeviceId;
    }
    public long getUtcTimestamp() {
        return utcTimestamp;
    }
    public void setUtcTimestamp(long utcTimestamp) {
        this.utcTimestamp = utcTimestamp;
    }
    public ITSS_Geofence getItssGeofence() {
        return itssGeofence;
    }
    public void setItssGeofence(ITSS_Geofence itssGeofence) {
        this.itssGeofence = itssGeofence;
    }
    public String getItssGeofenceEventTrigger() {
        return itssGeofenceEventTrigger;
    }
    public void setItssGeofenceEventTrigger(String itssGeofenceEventTrigger) {
        this.itssGeofenceEventTrigger = itssGeofenceEventTrigger;
    }
    public GNSS_Position getGnssPosition() {
        return gnssPosition;
    }
    public void setGnssPosition(GNSS_Position gnssPosition) {
        this.gnssPosition = gnssPosition;
    }
    public String getItssTelematicsApplicationId() {
        return itssTelematicsApplicationId;
    }
    public void setItssTelematicsApplicationId(String itssTelematicsApplicationId) {
        this.itssTelematicsApplicationId = itssTelematicsApplicationId;
    }



}
