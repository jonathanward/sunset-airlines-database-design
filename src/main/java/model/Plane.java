package model;

public class Plane {

    private int planeId;
    private String planeName;
    private String planeType;
    private int passengerCapacity;

    public Plane() {}

    public Plane(int planeId, String planeName, String planeType, int passengerCapacity) {
        this.planeId = planeId;
        this.planeName = planeName;
        this.planeType = planeType;
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public String toString() {
        return "-----------------------\r\n" +
                planeName.toUpperCase() +
                "\r\n" +
                "-----------------------\r\n" +
                "\r\nPlane id: " +
                planeId +
                "\r\nPlane type: " +
                planeType +
                "\r\nPassenger capacity: " +
                passengerCapacity +
                "\r\n";
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
