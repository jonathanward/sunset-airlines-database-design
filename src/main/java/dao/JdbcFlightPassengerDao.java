package dao;

import model.FlightPassenger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightPassengerDao implements FlightPassengerDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcFlightPassengerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public FlightPassenger getFlightPassengerFromFlightId(int flightId) {
        FlightPassenger flightPassenger = null;
        String sql = "SELECT flight_id, passenger_id, date_booked" +
                "FROM flight_passenger " +
                "WHERE flight_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, flightId);
        if (results.next()) {
            flightPassenger = mapRowToFlightPassenger(results);
        }
        return flightPassenger;
    }

    @Override
    public FlightPassenger getFlightPassengerFromPassengerId(int passengerId) {
        FlightPassenger flightPassenger = null;
        String sql = "SELECT flight_id, passenger_id, date_booked" +
                "FROM flight_passenger " +
                "WHERE passenger_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, passengerId);
        if (results.next()) {
            flightPassenger = mapRowToFlightPassenger(results);
        }
        return flightPassenger;
    }

    @Override
    public FlightPassenger createFlightPassenger(FlightPassenger newFlightPassenger) {
        String sql = "INSERT INTO flight_passenger (flight_id, passenger_id, date_booked) " +
                "VALUES (?, ?) RETURNING flight_id, passenger_id, date_booked;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newFlightPassenger.getFlightId(), newFlightPassenger.getPassengerId());
        FlightPassenger flightPassengerToReturn = new FlightPassenger();
        if (results.next()) {
            flightPassengerToReturn = mapRowToFlightPassenger(results);
        }
        return flightPassengerToReturn;
    }

    @Override
    public List<FlightPassenger> getAllFlightPassengers() {
        List<FlightPassenger> flightPassengers = new ArrayList<>();
        String sql = "SELECT flight_id, passenger_id, date_booked" +
                "FROM flight_passenger;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            flightPassengers.add(mapRowToFlightPassenger(results));
        }
        return flightPassengers;
    }

    @Override
    public void deleteFlightPassenger(int flightId, int passengerId) {
        String sql = "DELETE FROM flight_passenger WHERE flight_id = ? AND passenger_id = ?;";
        jdbcTemplate.update(sql, flightId, passengerId);
    }

    @Override
    public void deleteFlightPassengerFromFlightId(int flightId) {
        String sql = "DELETE FROM flight_passenger WHERE flight_id = ?;";
        jdbcTemplate.update(sql, flightId);
    }

    @Override
    public void deleteFlightPassengerFromPassengerId(int passengerId) {
        String sql = "DELETE FROM flight_passenger WHERE passenger_id = ?;";
        jdbcTemplate.update(sql, passengerId);
    }

    private FlightPassenger mapRowToFlightPassenger(SqlRowSet results) {
        FlightPassenger flightPassenger = new FlightPassenger();
        flightPassenger.setFlightId(results.getInt("flight_id"));
        flightPassenger.setPassengerId(results.getInt("passenger_id"));
        flightPassenger.setDateBooked(results.getTimestamp("date_booked").toLocalDateTime());
        return flightPassenger;
    }

}
