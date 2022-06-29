package dao;

import model.Passenger;

public interface PassengerDao {

    Passenger getPassenger(int passengerId);

    Passenger getPassengerByLastName(String lastName);

    Passenger getPassengerByFlyerNumber(String flyerNumber);

    void updatePassenger(Passenger updatedPassenger);

    void deletePassenger(int passengerId);

}
