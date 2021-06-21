package itss.type.theftex;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationValue {
	BigDecimal lat;
	BigDecimal lng;
	
	public BigDecimal getLat() {
		return lat;
	}
	
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	
	public BigDecimal getLng() {
		return lng;
	}
	
	@JsonProperty("long")
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	
	
}
