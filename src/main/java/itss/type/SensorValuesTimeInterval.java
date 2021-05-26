package itss.type;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorValuesTimeInterval {

    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "ITSS_SensorValueList")
    List <ITSS_SensorValue> itssSensorValueList;
    @JsonProperty(value = "ITSS_TelematicsApplicationID")
    String itssTelematicsApplicationId;


    public void addItssSensorValue( ITSS_SensorValue itssSensorValue ) {
        if ( itssSensorValueList == null )
            itssSensorValueList = new LinkedList<ITSS_SensorValue>();
        itssSensorValueList.add( itssSensorValue );
    }


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


    public List<ITSS_SensorValue> getItssSensorValueList() {
        return itssSensorValueList;
    }


    public void setItssSensorValueList(List<ITSS_SensorValue> itssSensorValueList) {
        this.itssSensorValueList = itssSensorValueList;
    }


    public String getItssTelematicsApplicationId() {
        return itssTelematicsApplicationId;
    }


    public void setItssTelematicsApplicationId(String itssTelematicsApplicationId) {
        this.itssTelematicsApplicationId = itssTelematicsApplicationId;
    }

}
