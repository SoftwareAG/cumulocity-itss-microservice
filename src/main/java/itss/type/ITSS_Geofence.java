package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ITSS_Geofence {

    @JsonProperty(value = "GeofenceID")
    String geofenceId;
    @JsonProperty(value = "GeofenceName")
    String geofenceName;

    public String getGeofenceId() {
        return geofenceId;
    }
    public void setGeofenceId(String geofenceId) {
        this.geofenceId = geofenceId;
    }
    public String getGeofenceName() {
        return geofenceName;
    }
    public void setGeofenceName(String geofenceName) {
        this.geofenceName = geofenceName;
    }

}