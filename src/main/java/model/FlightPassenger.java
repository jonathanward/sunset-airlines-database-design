package model;

import java.time.LocalDate;

public class FlightPassenger {

    private int flightId;
    private int passengerId;
    private LocalDate dateBooked;

    public FlightPassenger() {}

    public FlightPassenger(int flightId, int passengerId, LocalDate dateBooked) {
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.dateBooked = dateBooked;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }
}
