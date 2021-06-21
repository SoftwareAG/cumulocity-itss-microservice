package itss.type.theftex;

import java.util.List;

public class SensorDataResponse {
	int page;
    int size;
    int totalElements;
    int totalPages;
    List<SensorData> content;


    public List<SensorData> getContent() {
        return content;
    }

    public void setContent(List<SensorData> content) {
        this.content = content;
    }
}
