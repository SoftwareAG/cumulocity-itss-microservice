package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shock {
    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "ITSS_TelematicsApplicationID")
    String itssTelematicsApplicationId;
    @JsonProperty(value = "GNSS_Position")
    GNSS_Position gnssPosition;
    @JsonProperty("UTCtimestamp")
    BigDecimal timestamp;
    @JsonProperty("X-Axis_triggered")
    boolean xAxisTriggered;
    @JsonProperty("Y-Axis_triggered")
    boolean yAxisTriggered;
    @JsonProperty("Z-Axis_triggered")
    boolean zAxisTriggered;
    @JsonProperty("X-Axis")
    BigDecimal xAxis;
    @JsonProperty("Y-Axis")
    BigDecimal yAxis;
    @JsonProperty("Z-Axis")
    BigDecimal zAxis;

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

    public GNSS_Position getGnssPosition() {
        return gnssPosition;
    }

    public void setGnssPosition(GNSS_Position gnssPosition) {
        this.gnssPosition = gnssPosition;
    }

    public BigDecimal getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigDecimal timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isxAxisTriggered() {
        return xAxisTriggered;
    }

    public void setxAxisTriggered(boolean xAxisTriggered) {
        this.xAxisTriggered = xAxisTriggered;
    }

    public boolean isyAxisTriggered() {
        return yAxisTriggered;
    }

    public void setyAxisTriggered(boolean yAxisTriggered) {
        this.yAxisTriggered = yAxisTriggered;
    }

    public boolean iszAxisTriggered() {
        return zAxisTriggered;
    }

    public void setzAxisTriggered(boolean zAxisTriggered) {
        this.zAxisTriggered = zAxisTriggered;
    }

    public BigDecimal getxAxis() {
        return xAxis;
    }

    public void setxAxis(BigDecimal xAxis) {
        this.xAxis = xAxis;
    }

    public BigDecimal getyAxis() {
        return yAxis;
    }

    public void setyAxis(BigDecimal yAxis) {
        this.yAxis = yAxis;
    }

    public BigDecimal getzAxis() {
        return zAxis;
    }

    public void setzAxis(BigDecimal zAxis) {
        this.zAxis = zAxis;
    }
}
