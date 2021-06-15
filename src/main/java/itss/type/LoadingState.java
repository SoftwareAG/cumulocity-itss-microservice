package itss.type;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoadingState {
    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "UTCtimestamp")
    BigDecimal utcTimestamp;
    @JsonProperty(value = "GNSS_Position")
    GNSS_Position gnssPosition;
    @JsonProperty(value = "ITSS_LoadingState")
    String itssLoadingState;
    @JsonProperty(value = "payload")
    long payload;
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
    public BigDecimal getFromUTCTimestamp() {
        return utcTimestamp;
    }
    public void setFromUTCTimestamp(BigDecimal utcTimestamp) {
        this.utcTimestamp = utcTimestamp;
    }
    public GNSS_Position getGnssPosition() {
        return gnssPosition;
    }
    public void setGnssPosition(GNSS_Position gnssPosition) {
        this.gnssPosition = gnssPosition;
    }
    public String getItssLoadingState() {
        return itssLoadingState;
    }
    public void setItssLoadingState(String itssLoadingState) {
        this.itssLoadingState = itssLoadingState;
    }
    public long getPayload() {
        return payload;
    }
    public void setPayload(long payload) {
        this.payload = payload;
    }
    public String getItssTelematicsApplicationId() {
        return itssTelematicsApplicationId;
    }
    public void setItssTelematicsApplicationId(String itssTelematicsApplicationId) {
        this.itssTelematicsApplicationId = itssTelematicsApplicationId;
    }
}
