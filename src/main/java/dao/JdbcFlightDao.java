package dao;

import model.Airport;
import model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightDao implements FlightDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcFlightDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Flight getFlight(int flightId) {
        Flight flight = null;
        String sql = "SELECT flight_id, flight_number, departure_airport, arrival_airport, departure_date, departure_time, arrival_date, arrival_time, plane_id " +
                "FROM flight " +
                "WHERE flight = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, flightId);
        if (results.next()) {
            flight = mapRowToFlight(results);
        }
        return flight;
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

    private Flight mapRowToFlight(SqlRowSet results) {
        Flight flight = new Flight();
        flight.setFlightId(results.getInt("flight_id"));
        flight.setFlightNumber(results.getString("flight_number"));
        flight.setDepartureAirport(results.getString("departure_airport"));
        flight.setArrivalAirport(results.getString("arrival_airport"));
        flight.setDepartureDate(results.getDate("departure_date").toLocalDate());
        flight.setDepartureTime(results.getTime("departure_time").toLocalTime());
        flight.setArrivalDate(results.getDate("arrival_date").toLocalDate());
        flight.setDepartureDate(results.getDate("departure_date").toLocalDate());
        flight.setArrivalDate(results.getDate("arrival_date").toLocalDate());
        flight.setPlaneId(results.getInt("plane_id"));

        return flight;
    }

}
