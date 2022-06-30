package dao;

import model.Flight;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightDaoTests extends BaseDaoTests {

    private JdbcFlightDao sut;
    private static final Flight SA100 = new Flight(1, "SA 100", "AAA", "BBB", LocalDate.of(2023, 1, 1), LocalTime.of(11, 0), LocalDate.of(2023, 1, 2), LocalTime.of(1, 30), 1);
    private static final Flight SA200 = new Flight(2, "SA 200", "CCC", "DDD", LocalDate.of(2023, 1, 2), LocalTime.of(12, 0), LocalDate.of(2023, 1, 2), LocalTime.of(22, 00), 2);
    private Flight testFlight;

    @Before
    public void setup() {
        sut = new JdbcFlightDao(dataSource);
        testFlight = new Flight(0, "SA 300", "YYY", "ZZZ", LocalDate.of(2024, 1, 1), LocalTime.of(12, 0), LocalDate.of(2024, 1, 1), LocalTime.of(1, 0), 2);
    }

    @Test
    public void getFlight_returns_correct_flight_for_id() {
        Flight flight = sut.getFlight(1);
        assertFlightsMatch(SA100, flight);
    }

    @Test
    public void getAllFlights_returns_list_of_all_flights() {
        List<Flight> flightsExpected = new ArrayList<>();
        flightsExpected.add(SA100);
        flightsExpected.add(SA200);

        List<Flight> flightsActual = sut.getAllFlights();
        Assert.assertEquals(flightsExpected.size(), flightsActual.size());

        assertFlightsMatch(SA100, flightsActual.get(0));
        assertFlightsMatch(SA200, flightsActual.get(1));
    }

    @Test
    public void updateFlight_updates_correct_values() {
        int flightId = 1;
        Flight flightToUpdate = sut.getFlight(flightId);
        flightToUpdate.setFlightNumber("SA 900");
        flightToUpdate.setDepartureDate(LocalDate.of(2022, 9, 12));
        flightToUpdate.setArrivalDate(LocalDate.of(2022, 9, 12));
        flightToUpdate.setArrivalTime(LocalTime.of(19, 30));

        sut.updateFlight(flightToUpdate);
        Flight actualFlight = sut.getFlight(flightId);
        assertFlightsMatch(flightToUpdate, actualFlight);
    }

    private static void assertFlightsMatch(Flight expected, Flight actual) {
        Assert.assertEquals(expected.getFlightId(), actual.getFlightId());
        Assert.assertEquals(expected.getFlightNumber(), actual.getFlightNumber());
        Assert.assertEquals(expected.getDepartureAirport(), actual.getDepartureAirport());
        Assert.assertEquals(expected.getArrivalAirport(), actual.getArrivalAirport());
        Assert.assertEquals(expected.getDepartureDate(), actual.getDepartureDate());
        Assert.assertEquals(expected.getDepartureTime(), actual.getDepartureTime());
        Assert.assertEquals(expected.getArrivalDate(), actual.getArrivalDate());
        Assert.assertEquals(expected.getArrivalTime(), actual.getArrivalTime());
        Assert.assertEquals(expected.getPlaneId(), actual.getPlaneId());
    }

}
