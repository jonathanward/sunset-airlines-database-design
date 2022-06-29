package dao;

import model.Airport;

import java.util.List;

public interface AirportDao {

    Airport getAirport(int airportId);

    Airport getAirportByIataCode(String iataCode);

    Airport getAirportByCity(String city);

    Airport createAirport(Airport newAirport);

    List<Airport> getAllAirports();

    void updateAirport(Airport updatedAirport);

    void deleteAirport(int airportId);

}
