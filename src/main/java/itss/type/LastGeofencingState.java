package itss.type;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LastGeofencingState {
    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "UTCtimestamp")
    long utcTimestamp;
    @JsonProperty(value = "ITSS_GeofenceList")
    List <ITSS_Geofence> itssGeofenceList;
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
    public List<ITSS_Geofence> getItssSensorValueList() {
        return itssGeofenceList;
    }
    public void setItssSensorValueList(List<ITSS_Geofence> itssGeofenceList) {
        this.itssGeofenceList = itssGeofenceList;
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

    public void addItssGeofence( ITSS_Geofence itssGeofence ) {
        if ( itssGeofenceList == null )
            itssGeofenceList = new LinkedList<ITSS_Geofence>();
        itssGeofenceList.add( itssGeofence );
    }


}