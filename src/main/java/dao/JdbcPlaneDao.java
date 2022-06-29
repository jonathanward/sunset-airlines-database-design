package dao;

import model.Plane;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;

public class JdbcPlaneDao implements PlaneDao {

    @Override
    public Plane getPlane(int planeId) {
        return new Plane();
    }

    @Override
    public Plane createPlane(Plane newPlane) {
        return new Plane();
    }

    @Override
    public void updatePlane(Plane updatedPlane) {

    }

    @Override
    public void deletePlane(int planeId) {

    }

}
