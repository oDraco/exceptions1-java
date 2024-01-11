package Model.Entities;

import Model.Exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private final static DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
        if(checkIn.isAfter(checkOut)) {
            throw new DomainException("Check-out date must be after check-in date.");
        }
        if(checkIn.isBefore(LocalDate.now()) || checkIn.isBefore(LocalDate.now())) {
            throw new DomainException("Reservation dates must be future dates.");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void updateDates(LocalDate checkIn, LocalDate checkOut) {
        if(checkIn.isAfter(checkOut)) {
            throw new DomainException("Check-out date must be after check-in date.");
        }
        if(checkIn.isBefore(LocalDate.now()) || checkIn.isBefore(LocalDate.now())) {
            throw new DomainException("Reservation dates must be future dates.");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getDuration() {
        return checkOut.getDayOfYear()-checkIn.getDayOfYear();
    }

    @Override
    public String toString() {
        return "Room " + getRoomNumber() +
                ", Check-in: " + defaultFormatter.format(getCheckIn()) +
                ", Check-out: " + defaultFormatter.format(getCheckOut()) +
                ", " + getDuration() + " nights";
    }
}
