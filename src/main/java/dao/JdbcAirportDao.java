package dao;

import model.Airport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;

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
        return null;
    }

    @Override
    public Airport getAirportByCity(String city) {
        return null;
    }

    @Override
    public void createAirport(Airport newAirport) {

    }

    @Override
    public void updateAirport(Airport updatedAirport) {

    }

    @Override
    public void deleteAirport(int airportId) {

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
