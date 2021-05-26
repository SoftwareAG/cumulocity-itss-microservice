package itss.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ITSS_LocationInfo {
    @JsonProperty("Location_ZIP")
    String locationZip;
    @JsonProperty("Location_City")
    String locationCity;
    @JsonProperty("Location_Street")
    String locationStreet;
    @JsonProperty("Location_Description")
    String locationDescription;
    @JsonProperty("Location_Country")
    String locationCountry;
    @JsonProperty("Location_UIC_Code")
    String locationUICCode;
    @JsonProperty("Location_GeoZone")
    String locationGeoZone;
    //Added for Geofence and Last Movement related request and notification
    @JsonProperty("Location_POI_ID")
    String locationPoiId;

	public String getLocationZip() {
        return locationZip;
    }

    public void setLocationZip(String locationZip) {
        this.locationZip = locationZip;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationStreet() {
        return locationStreet;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getLocationUICCode() {
        return locationUICCode;
    }

    public void setLocationUICCode(String locationUICCode) {
        this.locationUICCode = locationUICCode;
    }

    public String getLocationGeoZone() {
        return locationGeoZone;
    }

    public void setLocationGeoZone(String locationGeoZone) {
        this.locationGeoZone = locationGeoZone;
    }

    public String getLocationPoiId() {
		return locationPoiId;
	}

	public void setLocationPoiId(String locationPoiId) {
		this.locationPoiId = locationPoiId;
	}

    @Override
    public String toString() {
        return "ITSS_LocationInfo{" +
                "locationZip='" + locationZip + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationStreet='" + locationStreet + '\'' +
                ", locationDescription='" + locationDescription + '\'' +
                ", locationCountry='" + locationCountry + '\'' +
                ", locationUICCode='" + locationUICCode + '\'' +
                ", locationGeoZone='" + locationGeoZone + '\'' +
                ", locationPoiId='" + locationPoiId + '\'' +
                '}';
    }
}
