package dao;

import model.FlightPassenger;

import java.util.List;

public interface FlightPassengerDao {

    FlightPassenger getFlightPassenger(int flightId, int passengerId);

    List<FlightPassenger> getFlightPassengersFromFlightId(int flightId);

    List<FlightPassenger> getFlightPassengersFromPassengerId(int passengerId);

    FlightPassenger createFlightPassenger(FlightPassenger newFlightPassenger);

    List<FlightPassenger> getAllFlightPassengers();

    void deleteFlightPassenger(int flightId, int passengerId);

    void deleteFlightPassengerFromFlightId(int flightId);

    void deleteFlightPassengerFromPassengerId(int flightId);

}
