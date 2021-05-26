package itss.type;


import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositionsTimeInterval {
    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "ITSS_TelematicsApplicationID")
    String itssTelematicsApplicationId;
    @JsonProperty(value = "GNSS_PositionList")
    List <GNSS_Position> gnssPositionList;


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
    public List <GNSS_Position> getGnssPositionList() {
        return gnssPositionList;
    }

    public void setGnssPositionList(List <GNSS_Position> gnssPositionList) {
        this.gnssPositionList = gnssPositionList;
    }

    public String getItssTelematicsApplicationId() {
        return itssTelematicsApplicationId;
    }

    public void setItssTelematicsApplicationId(String itssTelematicsApplicationId) {
        this.itssTelematicsApplicationId = itssTelematicsApplicationId;
    }

    public void addGnssPostion( GNSS_Position position ) {
    	if ( gnssPositionList == null ) 
    		gnssPositionList = new LinkedList<GNSS_Position>();
    	gnssPositionList.add( position );
    }
}
