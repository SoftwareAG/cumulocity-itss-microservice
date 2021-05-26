package itss.type;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllDevices {
    @JsonProperty(value = "ITSS_DeviceList")
    List <ITSS_Device> itssDeviceList;

    @JsonProperty(value = "ITSS_TelematicsApplicationID")
    String itssTelematicsApplicationId;

    public List<ITSS_Device> getItssDeviceList() {
        return itssDeviceList;
    }

    public void setItssDeviceList(List<ITSS_Device> itssDeviceList) {
        this.itssDeviceList = itssDeviceList;
    }

    public String getItssTelematicsApplicationId() {
        return itssTelematicsApplicationId;
    }

    public void setItssTelematicsApplicationId(String itssTelematicsApplicationId) {
        this.itssTelematicsApplicationId = itssTelematicsApplicationId;
    }

    public void addItssDevice( ITSS_Device itssDevice ) {
        if ( itssDeviceList == null )
            itssDeviceList = new LinkedList<ITSS_Device>();
        itssDeviceList.add( itssDevice );
    }
}