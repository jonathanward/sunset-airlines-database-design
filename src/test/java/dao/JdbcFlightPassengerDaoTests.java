package dao;

import model.FlightPassenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightPassengerDaoTests extends BaseDaoTests {

    private FlightPassengerDao sut;
    private static final LocalDateTime testDateTime = LocalDateTime.of(2022, Month.JUNE, 28, 16, 8, 23);
    private static final FlightPassenger FLIGHT_PASSENGER_1 = new FlightPassenger(1, 1, testDateTime);
    private static final FlightPassenger FLIGHT_PASSENGER_2 = new FlightPassenger(1, 3, testDateTime);
    private static final FlightPassenger FLIGHT_PASSENGER_3 = new FlightPassenger(1, 4, testDateTime);
    private static final FlightPassenger FLIGHT_PASSENGER_4 = new FlightPassenger(2, 1, testDateTime);
    private static final FlightPassenger FLIGHT_PASSENGER_5 = new FlightPassenger(2, 2, testDateTime);
    private static final FlightPassenger FLIGHT_PASSENGER_6 = new FlightPassenger(2, 3, testDateTime);
    private static final FlightPassenger FLIGHT_PASSENGER_7 = new FlightPassenger(2, 4, testDateTime);

    private FlightPassenger testFlightPassenger;

    @Before
    public void setup() {
        sut = new JdbcFlightPassengerDao(dataSource);
        testFlightPassenger = new FlightPassenger(1, 2, testDateTime);
    }

    @Test
    public void getFlightPassenger_returns_correct_flightPassenger_for_ids() {
        FlightPassenger flightPassenger = sut.getFlightPassenger(1, 1);
        assertFlightPassengersMatch(FLIGHT_PASSENGER_1, flightPassenger);
    }

    @Test
    public void getFlightPassengersFromFlightId_returns_correct_flightPassenger_list_for_id() {
        List<FlightPassenger> flightPassengersExpected = new ArrayList<>();
        flightPassengersExpected.add(FLIGHT_PASSENGER_1);
        flightPassengersExpected.add(FLIGHT_PASSENGER_2);
        flightPassengersExpected.add(FLIGHT_PASSENGER_3);

        List<FlightPassenger> flightPassengersActual = sut.getFlightPassengersFromFlightId(1);
        Assert.assertEquals(flightPassengersExpected.size(), flightPassengersActual.size());
        assertFlightPassengersMatch(FLIGHT_PASSENGER_1, flightPassengersActual.get(0));
    }

    @Test
    public void getAllFlightPassengers_returns_correct_flightPassenger_list() {
        List<FlightPassenger> flightPassengersExpected = new ArrayList<>();
        flightPassengersExpected.add(FLIGHT_PASSENGER_1);
        flightPassengersExpected.add(FLIGHT_PASSENGER_2);
        flightPassengersExpected.add(FLIGHT_PASSENGER_3);
        flightPassengersExpected.add(FLIGHT_PASSENGER_4);
        flightPassengersExpected.add(FLIGHT_PASSENGER_5);
        flightPassengersExpected.add(FLIGHT_PASSENGER_6);
        flightPassengersExpected.add(FLIGHT_PASSENGER_7);

        List<FlightPassenger> flightPassengersActual = sut.getAllFlightPassengers();
        Assert.assertEquals(flightPassengersExpected.size(), flightPassengersActual.size());
        assertFlightPassengersMatch(FLIGHT_PASSENGER_1, flightPassengersActual.get(0));
    }

    @Test
    public void createFlightPassenger_creates_correct_flightPassenger() {
        FlightPassenger actualFlightPassenger = sut.createFlightPassenger(testFlightPassenger);
        assertFlightPassengersMatch(testFlightPassenger, actualFlightPassenger);
    }

    @Test
    public void deleteFlightPassenger_deletes_correct_flightPassenger() {
        int flightId = 1;
        int passengerId = 3;
        sut.deleteFlightPassenger(flightId, passengerId);
        FlightPassenger deletedFlightPassenger = sut.getFlightPassenger(flightId, passengerId);
        Assert.assertNull(deletedFlightPassenger);
    }

    @Test
    public void deleteFlightPassengerFromFlightId_deletes_correct_flightPassenger() {
        int flightId = 1;
        int passengerId = 3;
        sut.deleteFlightPassengerFromFlightId(flightId);
        FlightPassenger deletedFlightPassenger = sut.getFlightPassenger(flightId, passengerId);
        Assert.assertNull(deletedFlightPassenger);
    }

    @Test
    public void deleteFlightPassengerFromPassengerId_deletes_correct_flightPassenger() {
        int flightId = 2;
        int passengerId = 4;
        sut.deleteFlightPassengerFromPassengerId(passengerId);
        FlightPassenger deletedFlightPassenger = sut.getFlightPassenger(flightId, passengerId);
        Assert.assertNull(deletedFlightPassenger);
    }

    private void assertFlightPassengersMatch(FlightPassenger expected, FlightPassenger actual) {
        Assert.assertEquals(expected.getFlightId(), actual.getFlightId());
        Assert.assertEquals(expected.getPassengerId(), actual.getPassengerId());
    }

}
