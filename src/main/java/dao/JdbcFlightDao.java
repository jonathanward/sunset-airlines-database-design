package dao;

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
                "WHERE flight_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, flightId);
        if (results.next()) {
            flight = mapRowToFlight(results);
        }
        return flight;
    }

    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT flight_id, flight_number, departure_airport, arrival_airport, departure_date, departure_time, arrival_date, arrival_time, plane_id " +
                "FROM flight ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            flights.add(mapRowToFlight(results));
        }
        return flights;
    }

    // TODO Add createFlight method

    @Override
    public void updateFlight(Flight updatedFlight) {
        String sql = "UPDATE flight SET flight_number = ?, departure_airport = ?, arrival_airport = ?, departure_date = ?, departure_time = ?, arrival_date = ?, arrival_time = ?, plane_id = ? " +
                "WHERE flight_id = ?;";
        jdbcTemplate.update(sql, updatedFlight.getFlightNumber(), updatedFlight.getDepartureAirport(), updatedFlight.getArrivalAirport(), updatedFlight.getDepartureDate(), updatedFlight.getDepartureTime(), updatedFlight.getArrivalDate(), updatedFlight.getArrivalTime(), updatedFlight.getPlaneId(), updatedFlight.getFlightId());
    }

    @Override
    public void deleteFlight(int flightId) {
        // TODO Method body
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
        flight.setArrivalTime(results.getTime("arrival_time").toLocalTime());
        flight.setPlaneId(results.getInt("plane_id"));

        return flight;
    }

}
