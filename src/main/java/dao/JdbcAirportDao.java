package dao;

import model.Airport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcAirportDao implements AirportDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcAirportDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Airport getAirport(int airportId) {
        Airport airport = null;
        String sql = "SELECT airport_id, iata_code, airport_name, city, state, province, territory, country " +
                "FROM airport " +
                "WHERE airport_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, airportId);
        if (results.next()) {
            airport = mapRowToAirport(results);
        }
        return airport;
    }

    @Override
    public Airport getAirportByIataCode(String iataCode) {
        Airport airport = null;
        String sql = "SELECT airport_id, iata_code, airport_name, city, state, province, territory, country " +
                "FROM airport " +
                "WHERE iata_code = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, iataCode);
        if (results.next()) {
            airport = mapRowToAirport(results);
        }
        return airport;
    }

    @Override
    public Airport getAirportByCity(String city) {
        Airport airport = null;
        String sql = "SELECT airport_id, iata_code, airport_name, city, state, province, territory, country " +
                "FROM airport " +
                "WHERE city = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, city);
        if (results.next()) {
            airport = mapRowToAirport(results);
        }
        return airport;
    }

    @Override
    public Airport createAirport(Airport newAirport) {
        String sql = "INSERT INTO airport (iata_code, airport_name, city, state, country) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING airport_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newAirport.getIataCode(), newAirport.getAirportName(), newAirport.getCity(), newAirport.getState(), newAirport.getCountry());
        return getAirport(newId);
    }

    @Override
    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT airport_id, iata_code, airport_name, city, state, province, territory, country " +
                "FROM airport ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            airports.add(mapRowToAirport(results));
        }
        return airports;
    }

    @Override
    public void updateAirport(Airport updatedAirport) {
        // TODO Add ability to update province territory
        String sql = "UPDATE airport SET iata_code = ?, airport_name = ?, city = ?, state = ?, country = ? " +
                "WHERE airport_id = ?;";
        jdbcTemplate.update(sql, updatedAirport.getIataCode(), updatedAirport.getAirportName(), updatedAirport.getCity(), updatedAirport.getState(), updatedAirport.getCountry(), updatedAirport.getAirportId());
    }

    @Override
    public void deleteAirport(int airportId) {
        Airport airportToDelete = getAirport(airportId);
        String sql = "DELETE FROM flight_passenger WHERE flight_id = (SELECT flight_id FROM flight WHERE arrival_airport = ? OR departure_airport = ?);";
        jdbcTemplate.update(sql, airportToDelete.getIataCode(), airportToDelete.getIataCode());
        sql = "DELETE FROM flight WHERE departure_airport = ? OR arrival_airport = ?;";
        jdbcTemplate.update(sql, airportToDelete.getIataCode(), airportToDelete.getIataCode());
        sql = "DELETE FROM airport WHERE airport_id = ?;";
        jdbcTemplate.update(sql, airportId);
    }

    private Airport mapRowToAirport(SqlRowSet results) {
        Airport airport = new Airport();
        airport.setAirportId(results.getInt("airport_id"));
        airport.setIataCode(results.getString("iata_code"));
        airport.setAirportName(results.getString("airport_name"));
        airport.setCity(results.getString("city"));
        airport.setState(results.getString("state"));
        airport.setProvince(results.getString("province"));
        airport.setTerritory(results.getString("territory"));
        airport.setCountry(results.getString("country"));
        return airport;
    }

}
