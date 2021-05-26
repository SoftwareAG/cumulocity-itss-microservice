package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MileageTimeInterval extends Mileage{

    @JsonProperty(value = "From_UTCtimestamp")
    long fromUTCTimestamp;
    @JsonProperty(value = "To_UTCtimestamp")
    long toUTCTimestamp;

    public long getFromUTCTimestamp() {
        return fromUTCTimestamp;
    }

    public void setFromUTCTimestamp(long fromUTCTimestamp) {
        this.fromUTCTimestamp = fromUTCTimestamp;
    }

    public long getToUTCTimestamp() {
        return toUTCTimestamp;
    }

    public void setToUTCTimestamp(long toUTCTimestamp) {
        this.toUTCTimestamp = toUTCTimestamp;
    }
}