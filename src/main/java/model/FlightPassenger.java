package model;

import java.time.LocalDateTime;

public class FlightPassenger {

    private int flightId;
    private int passengerId;
    private LocalDateTime dateBooked;

    public FlightPassenger() {}

    public FlightPassenger(int flightId, int passengerId, LocalDateTime dateBooked) {
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.dateBooked = dateBooked;
    }

    @Override
    public String toString() {
        return "\r\nFlight id: " +
                flightId +
                "\r\nPassenger id: " +
                passengerId +
                "\r\nDate booked: " +
                dateBooked +
                "\r\n";
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

    public LocalDateTime getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDateTime dateBooked) {
        this.dateBooked = dateBooked;
    }
}
