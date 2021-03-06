package dao;

import model.Plane;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JdbcPlaneDaoTests extends BaseDaoTests {

    private JdbcPlaneDao sut;
    private static final Plane PLANE_1 = new Plane(1, "plane-1", "type-1", 10);
    private static final Plane PLANE_2 = new Plane(2, "plane-2", "type-2", 20);

    private Plane testPlane;

    @Before
    public void setup() {
        sut = new JdbcPlaneDao(dataSource);
        testPlane = new Plane(0, "plane-10", "type-10", 100);
    }

    @Test
    public void getPlane_returns_correct_plane_for_id() {
        Plane plane = sut.getPlane(1);
        assertPlanesMatch(PLANE_1, plane);
    }

    @Test
    public void getPlane_returns_null_for_invalid_id() {
        Plane plane = sut.getPlane(12);
        Assert.assertNull(plane);
    }

    @Test
    public void createPlane_returns_plane_with_correct_values() {
        Plane actualPlane = sut.createPlane(testPlane);
        testPlane.setPlaneId(3);
        assertPlanesMatch(testPlane, actualPlane);
    }

    @Test
    public void updatePlane_updates_correct_values() {
        int planeId = 2;
        Plane planeToUpdate = sut.getPlane(planeId);
        planeToUpdate.setPlaneName("Updated Plane");
        planeToUpdate.setPlaneType("Updated Type");
        planeToUpdate.setPassengerCapacity(1200);

        sut.updatePlane(planeToUpdate);
        Plane actualPlane = sut.getPlane(planeId);
        assertPlanesMatch(planeToUpdate, actualPlane);
    }

    @Test
    public void deletePlane_deletes_correct_plane() {
        int planeId = 1;
        sut.deletePlane(planeId);
        Plane planeToDelete = sut.getPlane(planeId);
        Assert.assertNull(planeToDelete);
    }

    private void assertPlanesMatch(Plane expected, Plane actual) {
        Assert.assertEquals(expected.getPlaneId(), actual.getPlaneId());
        Assert.assertEquals(expected.getPlaneName(), actual.getPlaneName());
        Assert.assertEquals(expected.getPlaneType(), actual.getPlaneType());
        Assert.assertEquals(expected.getPassengerCapacity(), actual.getPassengerCapacity());
    }
}
