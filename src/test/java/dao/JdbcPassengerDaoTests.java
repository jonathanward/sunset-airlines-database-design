package dao;

import model.Passenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class JdbcPassengerDaoTests extends BaseDaoTests {

    private JdbcPassengerDao sut;
    private static final Passenger PASSENGER_1 = new Passenger(1, "ABCDEFG", "first1", "last1", LocalDate.of(1992, 1, 1), "Platinum");
    private static final Passenger PASSENGER_2 = new Passenger(2, "BCDEFGH", "first2", "last2", LocalDate.of(1993, 1, 1), "Gold");
    private static final Passenger PASSENGER_3 = new Passenger(3, "CDEFGHI", "first3", "last3", LocalDate.of(1998, 1, 1), "Gold");
    private static final Passenger PASSENGER_4 = new Passenger(4, "DEFGHIJ", "first4", "last4", LocalDate.of(2000, 1, 1), "Executive");

    @Before
    public void setup() {
        sut = new JdbcPassengerDao(dataSource);
    }

    @Test
    public void getPassenger_returns_correct_passenger_for_id() {
        Passenger passenger = sut.getPassenger(1);
        assertPassengersMatch(PASSENGER_1, passenger);
    }

    @Test
    public void getPassengerByLastName_returns_correct_passenger_for_lastName() {
        Passenger passenger = sut.getPassengerByLastName("last2");
        assertPassengersMatch(PASSENGER_2, passenger);
    }

    @Test
    public void getPassengerByFlyerNumber_returns_correct_passenger_for_FlyerNumber() {
        Passenger passenger = sut.getPassengerByFlyerNumber("CDEFGHI");
        assertPassengersMatch(PASSENGER_3, passenger);
    }

    @Test
    public void getPassenger_returns_null_for_invalid_id() {
        Passenger passenger = sut.getPassenger(13);
        Assert.assertNull(passenger);
    }

    @Test
    public void updatePassenger_updates_correct_values() {
        int passengerId = 1;
        Passenger passengerToUpdate = sut.getPassenger(passengerId);
        passengerToUpdate.setStatus("Silver");
        passengerToUpdate.setLastName("last-test");
        passengerToUpdate.setBirthDate(LocalDate.of(2000, 12, 12));

        sut.updatePassenger(passengerToUpdate);
        Passenger actualPassenger = sut.getPassenger(passengerId);
        assertPassengersMatch(passengerToUpdate, actualPassenger);
    }

    @Test
    public void deletePassenger_deletes_correct_passenger() {
        int passengerId = 4;
        sut.deletePassenger(passengerId);
        Passenger deletedPassenger = sut.getPassenger(passengerId);
        Assert.assertNull(deletedPassenger);
    }

    private void assertPassengersMatch(Passenger expected, Passenger actual) {
        Assert.assertEquals(expected.getPassengerId(), actual.getPassengerId());
        Assert.assertEquals(expected.getFlyerNumber(), actual.getFlyerNumber());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.getStatus(), expected.getStatus());
    }

}
