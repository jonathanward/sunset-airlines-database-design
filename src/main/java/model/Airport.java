package model;

public class Airport {

    private int airportId;
    private String iataCode;
    private String airportName;
    private String city;
    private String state;
    private String province;
    private String territory;
    private String country;

    public Airport() {}

    public Airport(int airportId, String iataCode, String airportName, String city, String country) {
        this.airportId = airportId;
        this.iataCode = iataCode;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", iataCode='" + iataCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", province='" + province + '\'' +
                ", territory='" + territory + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
