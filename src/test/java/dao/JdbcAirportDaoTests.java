package dao;

import model.Airport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JdbcAirportDaoTests extends BaseDaoTests {

    private JdbcAirportDao sut;
    private static final Airport AIRPORT_1 = new Airport(1, "AAA", "A City Airport", "A City", "A State", "A Country");
    private static final Airport AIRPORT_2 = new Airport(2, "BBB", "B City Airport", "B City", "B State", "B Country");
    private static final Airport AIRPORT_3 = new Airport(3, "CCC", "C City Airport", "C City", "C State", "C Country");
    private static final Airport AIRPORT_4 = new Airport(4, "DDD", "D City Airport", "D City", "D State", "D Country");

    private Airport testAirport;

    @Before
    public void setup() {
        sut = new JdbcAirportDao(dataSource);
        testAirport = new Airport(0, "ZZZ", "Z City Airport", "Z City", "Z State", "Z Country");
    }

    @Test
    public void getAirport_returns_correct_airport_for_id() {
        Airport airport = sut.getAirport(1);
        assertAirportsMatch(AIRPORT_1, airport);
    }

    @Test
    public void getAirport_returns_null_for_invalid_id() {
        Airport airport = sut.getAirport(12);
        Assert.assertNull(airport);
    }

    @Test
    public void getAirportByIataCode_returns_correct_airport_from_iata_code() {
        Airport airport = sut.getAirportByIataCode("AAA");
        assertAirportsMatch(AIRPORT_1, airport);
    }

    @Test
    public void getAirportByCity_returns_correct_airport_from_city() {
        Airport airport = sut.getAirportByCity("A City");
        assertAirportsMatch(AIRPORT_1, airport);
    }

    @Test
    public void getAllAirports_returns_list_of_all_airports() {
        List<Airport> airportsExpected = new ArrayList<>();
        airportsExpected.add(AIRPORT_1);
        airportsExpected.add(AIRPORT_2);
        airportsExpected.add(AIRPORT_3);
        airportsExpected.add(AIRPORT_4);

        List<Airport> airportsActual = sut.getAllAirports();
        Assert.assertEquals(airportsExpected.size(), airportsActual.size());

        assertAirportsMatch(AIRPORT_1, airportsActual.get(0));
        assertAirportsMatch(AIRPORT_4, airportsActual.get(3));
    }

    @Test
    public void createAirport_returns_airport_with_id_and_expected_values() {
        Airport actualAirport = sut.createAirport(testAirport);

        testAirport.setAirportId(5);
        assertAirportsMatch(testAirport, actualAirport);
    }

    @Test
    public void updateAirport_updates_correct_values() {
        int airportId = 2;
        Airport airportToUpdate = sut.getAirport(airportId);
        // TODO Resolve FK constraint issue in testing
        // airportToUpdate.setIataCode("ABC");
        airportToUpdate.setAirportName("Updated International Airport");
        airportToUpdate.setCity("Updated City");
        airportToUpdate.setState("Updated State");
        airportToUpdate.setCountry("Updated Country");

        sut.updateAirport(airportToUpdate);
        Airport actualAirport = sut.getAirport(airportId);
        assertAirportsMatch(airportToUpdate, actualAirport);
    }

    @Test
    public void deleteAirport_deletes_correct_airport() {
        int airportId = 3;
        sut.deleteAirport(airportId);
        Airport deletedAirport = sut.getAirport(airportId);
        Assert.assertNull(deletedAirport);
    }

    private void assertAirportsMatch(Airport expected, Airport actual) {
        Assert.assertEquals(expected.getAirportId(), actual.getAirportId());
        Assert.assertEquals(expected.getIataCode(), actual.getIataCode());
        Assert.assertEquals(expected.getAirportName(), actual.getAirportName());
        Assert.assertEquals(expected.getCity(), actual.getCity());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getProvince(), actual.getProvince());
        Assert.assertEquals(expected.getTerritory(), actual.getTerritory());
        Assert.assertEquals(expected.getCountry(), actual.getCountry());
    }
}
