package Application;

import Model.Entities.Reservation;
import Model.Exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int roomNumber;
        LocalDate checkIn, checkOut;

        try {
            System.out.print("Room Number: ");
            roomNumber = sc.nextInt();
            sc.nextLine(); // "Consume" pending line break;


            System.out.print("Check-in (DD/MM/YYYY): ");
            checkIn = LocalDate.parse(sc.nextLine(), defaultFormatter);

            System.out.print("Check-out (DD/MM/YYYY): ");
            checkOut = LocalDate.parse(sc.nextLine(), defaultFormatter);

            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);

            System.out.println("\nEnter data to update the reservation:");
            System.out.print("Check-in (DD/MM/YYYY): ");
            checkIn = LocalDate.parse(sc.nextLine(), defaultFormatter);

            System.out.print("Check-out (DD/MM/YYYY): ");
            checkOut = LocalDate.parse(sc.nextLine(), defaultFormatter);

            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        }
        catch (DateTimeParseException e) {
            System.out.print("Invalid date format.");
        }
        catch (InputMismatchException e) {
            System.out.print("Invalid input type. Certifies the input is the requested type.");
        }
        catch (DomainException e) {
            System.out.print("Error in reservation: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Unexpected error. Printing stacktrace.");
            e.printStackTrace();
        }

        sc.close();
    }
}
