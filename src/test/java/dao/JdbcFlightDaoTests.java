package dao;

import model.Flight;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class JdbcFlightDaoTests extends BaseDaoTests {

    private JdbcFlightDao sut;
    private static final Flight SA100 = new Flight(1, "SA 100", "AAA", "BBB", LocalDate.of(2023, 1, 1), LocalTime.of(11, 0), LocalDate.of(2023, 1, 2), LocalTime.of(1, 30), 1);
    private static final Flight SA200 = new Flight(2, "SA 200", "CCC", "DDD", LocalDate.of(2023, 1, 2), LocalTime.of(12, 0), LocalDate.of(2023, 1, 2), LocalTime.of(22, 00), 1);
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
