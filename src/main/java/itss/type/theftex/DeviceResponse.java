package itss.type.theftex;

import java.util.List;

public class DeviceResponse {
	int page;
    int size;
    int totalElements;
    int totalPages;
//    MetaData metaData;
    String error;
    List<Device> content;

//    public MetaData getMetaData() {
//        return metaData;
//    }
//
//    public void setMetaData(MetaData metaData) {
//        this.metaData = metaData;
//    }
//
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Device> getContent() {
        return content;
    }

    public void setContent(List<Device> content) {
        this.content = content;
    }
}
