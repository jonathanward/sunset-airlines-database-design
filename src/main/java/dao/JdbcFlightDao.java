package dao;

import model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightDao implements FlightDao {

    @Override
    public Flight getFlight(int flightId) {
        return new Flight();
    }

    @Override
    public List<Flight> getAllFlights() {
        return new ArrayList<>();
    }

    @Override
    public void updateFlight(Flight updatedFlight) {

    }

    @Override
    public void deleteFlight(int flightId) {

    }
}
