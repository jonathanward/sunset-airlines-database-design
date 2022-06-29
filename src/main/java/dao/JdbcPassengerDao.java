package dao;

import model.Passenger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;

public class JdbcPassengerDao implements PassengerDao {

    @Override
    public Passenger getPassenger(int passengerId) {
        return new Passenger();
    }

    @Override
    public Passenger getPassengerByLastName(String lastName) {
        return new Passenger();
    }

    @Override
    public Passenger getPassengerByFlyerNumber(String flyerNumber) {
        return new Passenger();
    }

    @Override
    public void updatePassenger(Passenger updatedPassenger) {

    }

    @Override
    public void deletePassenger(int passengerId) {

    }

}
