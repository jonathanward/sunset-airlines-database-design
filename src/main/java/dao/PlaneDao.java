package dao;

import model.Plane;

public interface PlaneDao {

    Plane getPlane(int planeId);

    Plane createPlane(Plane newPlane);

    void updatePlane(Plane updatedPlane);

    void deletePlane(int planeId);

}
