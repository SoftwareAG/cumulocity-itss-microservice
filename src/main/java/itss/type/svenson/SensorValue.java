package itss.type.svenson;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.svenson.AbstractDynamicProperties;
import org.svenson.JSONProperty;

import java.math.BigDecimal;

public class SensorValue extends AbstractDynamicProperties {
    long samplingUTCTimestamp;
    String itssSensorId;
    BigDecimal value;
    String itssSensorType;
    String itssSensorPosition;

    public long getSamplingUTCTimestamp() {
        return samplingUTCTimestamp;
    }

    public void setSamplingUTCTimestamp(long samplingUTCTimestamp) {
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
    @JSONProperty(
            ignoreIfNull = true
    )
    public String getItssSensorType() {
        return itssSensorType;
    }

    public void setItssSensorType(String itssSensorType) {
        this.itssSensorType = itssSensorType;
    }
    @JSONProperty(
            ignoreIfNull = true
    )
    public String getItssSensorPosition() {
        return itssSensorPosition;
    }

    public void setItssSensorPosition(String itssSensorPosition) {
        this.itssSensorPosition = itssSensorPosition;
    }
}
