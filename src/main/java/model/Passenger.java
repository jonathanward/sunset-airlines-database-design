package model;

import java.time.LocalDate;

public class Passenger {

    private int passengerId;
    private String flyerNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String status;

    public Passenger() {}

    public Passenger(int passengerId, String flyerNumber, String firstName, String lastName, LocalDate birthDate, String status) {
        this.passengerId = passengerId;
        this.flyerNumber = flyerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.status = status;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getFlyerNumber() {
        return flyerNumber;
    }

    public void setFlyerNumber(String flyerNumber) {
        this.flyerNumber = flyerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
