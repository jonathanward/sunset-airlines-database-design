package dao;

import model.Passenger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;

public class JdbcPassengerDao implements PassengerDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcPassengerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Passenger getPassenger(int passengerId) {
        Passenger passenger = null;
        String sql = "SELECT passenger_id, flyer_number, first_name, last_name, birth_date, status " +
                "FROM passenger " +
                "WHERE passenger_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, passengerId);
        if (results.next()) {
            passenger = mapRowToPassenger(results);
        }
        return passenger;
    }

    @Override
    public Passenger getPassengerByLastName(String lastName) {
        Passenger passenger = null;
        String sql = "SELECT passenger_id, flyer_number, first_name, last_name, birth_date, status " +
                "FROM passenger " +
                "WHERE last_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, lastName);
        if (results.next()) {
            passenger = mapRowToPassenger(results);
        }
        return passenger;
    }

    @Override
    public Passenger getPassengerByFlyerNumber(String flyerNumber) {
        Passenger passenger = null;
        String sql = "SELECT passenger_id, flyer_number, first_name, last_name, birth_date, status " +
                "FROM passenger " +
                "WHERE flyer_number = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, flyerNumber);
        if (results.next()) {
            passenger = mapRowToPassenger(results);
        }
        return passenger;
    }

    @Override
    public void updatePassenger(Passenger updatedPassenger) {
        String sql = "UPDATE passenger SET flyer_number = ?, first_name = ?, last_name = ?, birth_date = ?, status = ? " +
                "WHERE passenger_id = ?;";
        // TODO Resolve null status issue
        jdbcTemplate.update(sql, updatedPassenger.getFlyerNumber(), updatedPassenger.getFirstName(), updatedPassenger.getLastName(), updatedPassenger.getBirthDate(), updatedPassenger.getStatus(), updatedPassenger.getPassengerId());
    }

    @Override
    public void deletePassenger(int passengerId) {
        String sql = "DELETE FROM flight_passenger WHERE passenger_id = ?;";
        jdbcTemplate.update(sql, passengerId);
        sql = "DELETE FROM passenger WHERE passenger_id = ?;";
        jdbcTemplate.update(sql, passengerId);
    }

    private Passenger mapRowToPassenger(SqlRowSet results) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(results.getInt("passenger_id"));
        passenger.setFlyerNumber(results.getString("flyer_number"));
        passenger.setFirstName(results.getString("first_name"));
        passenger.setLastName(results.getString("last_name"));
        passenger.setBirthDate(results.getDate("birth_date").toLocalDate());
        if (!results.getString("status").equals("null")) {
            passenger.setStatus("status");
        }
        return passenger;
    }

}
