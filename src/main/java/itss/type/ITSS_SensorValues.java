package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ITSS_SensorValues {
    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "ITSS_TelematicsApplicationID")
    String itssTelematicsApplicationId;
    @JsonProperty(value = "ITSS_SensorValueList")
    List<ITSS_SensorValue> itssSensorValueList;

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

    public String getItssTelematicsApplicationId() {
        return itssTelematicsApplicationId;
    }

    public void setItssTelematicsApplicationId(String itssTelematicsApplicationId) {
        this.itssTelematicsApplicationId = itssTelematicsApplicationId;
    }

    public List<ITSS_SensorValue> getItssSensorValueList() {
        return itssSensorValueList;
    }

    public void setItssSensorValueList(List<ITSS_SensorValue> itssSensorValueList) {
        this.itssSensorValueList = itssSensorValueList;
    }

    public void addItssSensorValue(ITSS_SensorValue itssSensorValue ) {
        if ( itssSensorValueList == null )
            itssSensorValueList = new LinkedList<ITSS_SensorValue>();
        itssSensorValueList.add( itssSensorValue );
    }
}
