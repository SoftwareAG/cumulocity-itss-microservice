package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssembledNotification {
    @JsonProperty(value = "ITSS_TransportDeviceID")
    String itssTransportDeviceId;
    @JsonProperty(value = "ITSS_TelematicsDeviceID")
    String itssTelematicsDeviceId;
    @JsonProperty(value = "UTCtimestamp")
    BigDecimal utcTimestamp;
    @JsonProperty(value = "GNSS_Position")
    GNSS_Position gnssPosition;
    @JsonProperty(value = "mileage")
    long mileage;
    @JsonProperty(value = "loadingState")
    String loadingState;
    @JsonProperty(value = "payload")
    BigDecimal payload;
    @JsonProperty(value = "ITSS_SensorValueList")
    List<ITSS_SensorValue> itssSensorValueList;
    @JsonProperty(value = "ITSS_GeofenceEventList")
    List<ITSS_Geofence> itssGeofenceList;
    @JsonProperty(value = "ITSS_MovementState")
    String movementState;
    @JsonProperty(value = "derailment_triggered")
    boolean derailment_trigggered;
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

    public BigDecimal getUtcTimestamp() {
        return utcTimestamp;
    }

    public void setUtcTimestamp(BigDecimal utcTimestamp) {
        this.utcTimestamp = utcTimestamp;
    }

    public GNSS_Position getGnssPosition() {
        return gnssPosition;
    }

    public void setGnssPosition(GNSS_Position gnssPosition) {
        this.gnssPosition = gnssPosition;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public String getLoadingState() {
        return loadingState;
    }

    public void setLoadingState(String loadingState) {
        this.loadingState = loadingState;
    }

    public BigDecimal getPayload() {
        return payload;
    }

    public void setPayload(BigDecimal payload) {
        this.payload = payload;
    }

    public List<ITSS_Geofence> getItssGeofenceList() {
        return itssGeofenceList;
    }

    public void setItssGeofenceList(List<ITSS_Geofence> itssGeofenceList) {
        this.itssGeofenceList = itssGeofenceList;
    }

    public String getMovementState() {
        return movementState;
    }

    public void setMovementState(String movementState) {
        this.movementState = movementState;
    }

    public boolean isDerailment_trigggered() {
        return derailment_trigggered;
    }

    public void setDerailment_trigggered(boolean derailment_trigggered) {
        this.derailment_trigggered = derailment_trigggered;
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

    public String getItssTelematicsApplicationId() {
        return itssTelematicsApplicationId;
    }

    public void setItssTelematicsApplicationId(String itssTelematicsApplicationId) {
        this.itssTelematicsApplicationId = itssTelematicsApplicationId;
    }

    public void addItssSensorValue(ITSS_SensorValue itssSensorValue) {
        if (itssSensorValueList == null)
            itssSensorValueList = new LinkedList<ITSS_SensorValue>();
        itssSensorValueList.add(itssSensorValue);
    }

    public List<ITSS_SensorValue> getItssSensorValueList() {
        return itssSensorValueList;
    }

    public void setItssSensorValueList(List<ITSS_SensorValue> itssSensorValueList) {
        this.itssSensorValueList = itssSensorValueList;
    }

    public void addItssGeofence(ITSS_Geofence itssGeofence) {
        if (itssGeofenceList == null)
            itssGeofenceList = new LinkedList<ITSS_Geofence>();
        itssGeofenceList.add(itssGeofence);
    }

    @Override
    public String toString() {
        return "AssembledNotification{" +
                "itssTransportDeviceId='" + itssTransportDeviceId + '\'' +
                ", itssTelematicsDeviceId='" + itssTelematicsDeviceId + '\'' +
                ", utcTimestamp=" + utcTimestamp +
                ", gnssPosition=" + gnssPosition +
                ", mileage=" + mileage +
                ", loadingState='" + loadingState + '\'' +
                ", payload=" + payload +
                ", itssSensorValueList=" + itssSensorValueList +
                ", itssGeofenceList=" + itssGeofenceList +
                ", movementState='" + movementState + '\'' +
                ", derailment_trigggered=" + derailment_trigggered +
                ", xAxisTriggered=" + xAxisTriggered +
                ", yAxisTriggered=" + yAxisTriggered +
                ", zAxisTriggered=" + zAxisTriggered +
                ", xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                ", zAxis=" + zAxis +
                ", itssTelematicsApplicationId='" + itssTelematicsApplicationId + '\'' +
                '}';
    }
}
