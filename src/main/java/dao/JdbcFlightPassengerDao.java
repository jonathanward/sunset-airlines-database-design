package dao;

import model.FlightPassenger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightPassengerDao implements FlightPassengerDao {

    @Override
    public FlightPassenger createFlightPassenger(FlightPassenger newFlightPassenger) {
        return newFlightPassenger;
    }

    @Override
    public List<FlightPassenger> getAllFlightPassengers() {
        return new ArrayList<>();
    }

    @Override
    public void updateFlightPassenger(FlightPassenger updatedFlightPassenger) {

    }

    @Override
    public void deleteFlightPassengerFromFlightId(int flightId) {

    }

    @Override
    public void deleteFlightPassengerFromPassengerId(int flightId) {

    }

}
