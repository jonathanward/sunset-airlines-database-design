package dao;

import model.Flight;

import java.util.List;

public interface FlightDao {

    Flight getFlight(int flightId);

    List<Flight> getAllFlights();

    void updateFlight(Flight updatedFlight);

    void deleteFlight(int flightId);

}
