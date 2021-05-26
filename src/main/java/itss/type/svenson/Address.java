package itss.type.svenson;

import org.svenson.AbstractDynamicProperties;
import org.svenson.JSONProperty;

public class Address extends AbstractDynamicProperties {

    private String city;
    private String territory;
    private String country;
    private String cityCode;
    private String region;
    private String street;

    @JSONProperty(
            ignoreIfNull = true
    )
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JSONProperty(
            ignoreIfNull = true
    )
    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @JSONProperty(
            ignoreIfNull = true
    )
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JSONProperty(
            ignoreIfNull = true
    )
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @JSONProperty(
            ignoreIfNull = true
    )
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
