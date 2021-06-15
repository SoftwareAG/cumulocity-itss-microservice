package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GNSS_Position {
    @JsonProperty("GNSS_UTCtimestamp")
    BigDecimal gnssUTCtimestamp;
    @JsonProperty("GNSS_Latitude")
    BigDecimal gnssLatitude;
    @JsonProperty("GNSS_Longitude")
    BigDecimal gnssLongitude;
    @JsonProperty("GNSS_Speed_kmph")
    BigDecimal gnssSpeedKmph;
    @JsonProperty("GNSS_Heading_deg")
    BigDecimal gnssHeadingDeg;
    @JsonProperty("GNSS_Altitude")
    BigDecimal gnssAltitude;
    @JsonProperty("GNSS_Accuracy")
    BigDecimal gnssAccuracy;
    @JsonProperty("ITSS_LocationInfo")
    ITSS_LocationInfo locationInfo;

    public BigDecimal getGnssUTCtimestamp() {
        return gnssUTCtimestamp;
    }

    public void setGnssUTCtimestamp(BigDecimal gnssUTCtimestamp) {
        this.gnssUTCtimestamp = gnssUTCtimestamp;
    }

    public BigDecimal getGnssLatitude() {
        return gnssLatitude;
    }

    public void setGnssLatitude(BigDecimal gnssLatitude) {
        this.gnssLatitude = gnssLatitude;
    }

    public BigDecimal getGnssLongitude() {
        return gnssLongitude;
    }

    public void setGnssLongitude(BigDecimal gnssLongitude) {
        this.gnssLongitude = gnssLongitude;
    }

    public BigDecimal getGnssSpeedKmph() {
        return gnssSpeedKmph;
    }

    public void setGnssSpeedKmph(BigDecimal gnssSpeedKmph) {
        this.gnssSpeedKmph = gnssSpeedKmph;
    }

    public BigDecimal getGnssHeadingDeg() {
        return gnssHeadingDeg;
    }

    public void setGnssHeadingDeg(BigDecimal gnssHeadingDeg) {
        this.gnssHeadingDeg = gnssHeadingDeg;
    }

    public BigDecimal getGnssAltitude() {
        return gnssAltitude;
    }

    public void setGnssAltitude(BigDecimal gnssAltitude) {
        this.gnssAltitude = gnssAltitude;
    }

    public BigDecimal getGnssAccuracy() {
        return gnssAccuracy;
    }

    public void setGnssAccuracy(BigDecimal gnssAccuracy) {
        this.gnssAccuracy = gnssAccuracy;
    }

    public ITSS_LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(ITSS_LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    @Override
    public String toString() {
        return "GNSS_Position{" +
                "gnssUTCtimestamp=" + gnssUTCtimestamp +
                ", gnssLatitude=" + gnssLatitude +
                ", gnssLongitude=" + gnssLongitude +
                ", gnssSpeedKmph=" + gnssSpeedKmph +
                ", gnssHeadingDeg=" + gnssHeadingDeg +
                ", gnssAltitude=" + gnssAltitude +
                ", gnssAccuracy=" + gnssAccuracy +
                ", locationInfo=" + locationInfo +
                '}';
    }
}
