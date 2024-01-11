package Application;

import Model.Entities.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int roomNumber;
        LocalDate checkIn, checkOut;

        System.out.print("Room Number: ");
        roomNumber = sc.nextInt();
        sc.nextLine(); // "Consume" pending line break;

        System.out.print("Check-in (DD/MM/YYYY): ");
        checkIn = LocalDate.parse(sc.nextLine(), defaultFormatter);

        System.out.print("Check-out (DD/MM/YYYY): ");
        checkOut = LocalDate.parse(sc.nextLine(), defaultFormatter);

        if(checkIn.isAfter(checkOut)) {
            System.out.println("Error in reservation: Check-out date must be after check-in date.");
        } else {
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);

            System.out.println("\nEnter data to update the reservation:");
            System.out.print("Check-in (DD/MM/YYYY): ");
            checkIn = LocalDate.parse(sc.nextLine(), defaultFormatter);

            System.out.print("Check-out (DD/MM/YYYY): ");
            checkOut = LocalDate.parse(sc.nextLine(), defaultFormatter);

            String error = reservation.updateDates(checkIn,checkOut);
            if(error != null) {
                System.out.println("Error in reservation: " + error);
            } else {
                System.out.println("Reservation: " + reservation);
            }
        }

        sc.close();
    }
}
