package model;

import java.util.Locale;

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

    public Airport(int airportId, String iataCode, String airportName, String city, String state, String country) {
        this.airportId = airportId;
        this.iataCode = iataCode;
        this.airportName = airportName;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < airportName.length(); i++) {
            stringBuilder.append("-");
        }
        stringBuilder.append("\r\n");
        stringBuilder.append(airportName.toUpperCase());
        stringBuilder.append("\r\n");
        for (int i = 0; i < airportName.length(); i++) {
            stringBuilder.append("-");
        }
        stringBuilder.append("\r\n");
        stringBuilder.append("\r\nAirport id: ");
        stringBuilder.append(airportId);
        stringBuilder.append("\r\nIata code: ");
        stringBuilder.append(iataCode);
        stringBuilder.append("\r\nCity: ");
        stringBuilder.append(city);
        if (state != null) {
            stringBuilder.append("\r\nState: ");
            stringBuilder.append(state);
        }
        if (province != null) {
            stringBuilder.append("\r\nProvince: ");
            stringBuilder.append(province);
        }
        if (territory != null) {
            stringBuilder.append("\r\nTerritory: ");
            stringBuilder.append(territory);
        }
        stringBuilder.append("\r\nCountry: ");
        stringBuilder.append(country);
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
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
