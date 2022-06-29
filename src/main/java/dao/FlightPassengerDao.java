package dao;

import model.FlightPassenger;

import java.util.List;

public interface FlightPassengerDao {

    FlightPassenger createFlightPassenger(FlightPassenger newFlightPassenger);

    List<FlightPassenger> getAllFlightPassengers();

    void updateFlightPassenger(FlightPassenger updatedFlightPassenger);

    void deleteFlightPassengerFromFlightId(int flightId);

    void deleteFlightPassengerFromPassengerId(int flightId);

}
