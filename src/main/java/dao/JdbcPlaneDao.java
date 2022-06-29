package dao;

import model.Plane;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;

public class JdbcPlaneDao implements PlaneDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcPlaneDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Plane getPlane(int planeId) {
        Plane plane = null;
        String sql = "SELECT plane_id, plane_name, plane_type, passenger_capacity " +
                "FROM plane " +
                "WHERE plane_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, planeId);
        if (results.next()) {
            plane = mapRowToPlane(results);
        }
        return plane;
    }

    @Override
    public Plane createPlane(Plane newPlane) {
        String sql = "INSERT INTO plane (plane_name, plane_type, passenger_capacity " +
                "VALUES (?, ?, ?) RETURNING plane_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newPlane.getPlaneName(), newPlane.getPlaneType(), newPlane.getPassengerCapacity());
        return getPlane(newId);
    }

    @Override
    public void updatePlane(Plane updatedPlane) {
        String sql = "UPDATE plane SET plane_name = ?, plane_type = ?, passenger_capacity = ? " +
                "WHERE plane_id = ?;";
        jdbcTemplate.update(sql, updatedPlane.getPlaneName(), updatedPlane.getPlaneType(), updatedPlane.getPassengerCapacity());
    }

    @Override
    public void deletePlane(int planeId) {
        String sql = "DELETE FROM flight_passenger WHERE flight_id = (SELECT flight_id FROM flight WHERE plane_id = ?);";
        jdbcTemplate.update(sql, planeId);
        sql = "DELETE FROM flight WHERE plane_id = ?;";
        jdbcTemplate.update(sql, planeId);
        sql = "DELETE FROM plane WHERE plane_id = ?;";
        jdbcTemplate.update(sql, planeId);
    }

    private Plane mapRowToPlane(SqlRowSet results) {
        Plane plane = new Plane();
        plane.setPlaneId(results.getInt("plane_id"));
        plane.setPlaneName(results.getString("plane_name"));
        plane.setPlaneType(results.getString("plane_type"));
        plane.setPassengerCapacity(results.getInt("passenger_capacity"));
        return plane;
    }

}
