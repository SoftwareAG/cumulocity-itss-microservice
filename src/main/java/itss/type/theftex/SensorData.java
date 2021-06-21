package itss.type.theftex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class SensorData {
	LocationValue location;
    String timestamp;
    Integer light;
    Integer temperature;
    

	public LocationValue getLocation() {
		return location;
	}
	@JsonProperty("LOCATION")
	public void setLocation(LocationValue location) {
		this.location = location;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getLight() {
		return light;
	}
	@JsonProperty("LIGHT")
	public void setLight(Integer light) {
		this.light = light;
	}
	public Integer getTemperature() {
		return temperature;
	}
	@JsonProperty("TEMPERATURE")
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	
	
    
}
