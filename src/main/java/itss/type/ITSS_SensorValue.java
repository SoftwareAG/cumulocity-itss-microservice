package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ITSS_SensorValue {

    @JsonProperty("SamplingUTCTimestamp")
    BigDecimal samplingUTCTimestamp;
    @JsonProperty("ITSS_SensorId")
    String itssSensorId;
    @JsonProperty("Value")
    BigDecimal value;
    @JsonProperty("ITSS_SensorType")
    String itssSensorType;
    @JsonProperty("ITSS_SensorPosition")
    String itssSensorPosition;

    public BigDecimal getSamplingUTCTimestamp() {
        return samplingUTCTimestamp;
    }
    public void setSamplingUTCTimestamp(BigDecimal samplingUTCTimestamp) {
        this.samplingUTCTimestamp = samplingUTCTimestamp;
    }
    public String getItssSensorId() {
        return itssSensorId;
    }
    public void setItssSensorId(String itssSensorId) {
        this.itssSensorId = itssSensorId;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public String getItssSensorType() {
        return itssSensorType;
    }
    public void setItssSensorType(String itssSensorType) {
        this.itssSensorType = itssSensorType;
    }
    public String getItssSensorPosition() {
        return itssSensorPosition;
    }
    public void setItssSensorPosition(String itssSensorPosition) {
        this.itssSensorPosition = itssSensorPosition;
    }

    @Override
    public String toString() {
        return "ITSS_SensorValue{" +
                "samplingUTCTimestamp=" + samplingUTCTimestamp +
                ", itssSensorId='" + itssSensorId + '\'' +
                ", value=" + value +
                ", itssSensorType='" + itssSensorType + '\'' +
                ", itssSensorPosition='" + itssSensorPosition + '\'' +
                '}';
    }
}
