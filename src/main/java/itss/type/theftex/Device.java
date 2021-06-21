package itss.type.theftex;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
	String id;
    String qrCode;
    String label;

    public String getQrCode() {
        return qrCode;
    }

    @JsonProperty("ID")
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getLabel() {
        return label;
    }
    
    @JsonProperty("TS_NAME")
    public void setLabel(String label) {
        this.label = label;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
