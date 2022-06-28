package dao;

import model.Airport;

public interface AirportDao {

    Airport getAirport(int airportId);

    Airport getAirportByIataCode(String iataCode);

    Airport getAirportByCity(String city);

    void createAirport(Airport newAirport);

    void updateAirport(Airport updatedAirport);

    void deleteAirport(int airportId);

}
