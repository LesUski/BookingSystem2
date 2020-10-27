package com.BokingSystem;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingSystem {
    private double price;
    private LocalDateTime date;
    private final Currency euro = Currency.getInstance("EUR");
    private final Currency krona = Currency.getInstance("SEK");
    private final Locale currentLocale = new Locale("sv", "SE");

    private void menu() throws IOException {
        System.out.println("\n\t\tWelcome to Booking System!\n" +
                "=======================================================\n" +
                "Please choose your option:\n" +
                "Choose [1] To choose country of destination.\n" +
                "Choose [2] To exit\n" +
                "=======================================================");
        while (true) {
            switch (getInt()) {
                case 1 -> chosenCountry();
                case 2 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> {
                    System.err.println("Please provide a number between 1 and 2");
                    System.out.println("=======================================================");
                }
            }
        }
    }

    private void chosenCountry() throws IOException {
        System.out.println("List of available countries: [1]Austria, [2]Germany, [3]Italy, [4]Spain\n" +
                "press [5] to exit");

        while (true) {
            switch (getInt()) {
                case 1 -> {
                    Austria austria = new Austria("Austria");
                    austria.getTravellingOptions();
                    price = austria.getPrice();
                    date = austria.getDate();
                    passengerDetails();
                }
                case 2 -> {
                    Germany germany = new Germany("Germany");
                    germany.getTravellingOptions();
                    price = germany.getPrice();
                    date = germany.getDate();
                    passengerDetails();
                }
                case 3 -> {
                    Italy italy = new Italy("Italy");
                    italy.getTravellingOptions();
                    price = italy.getPrice();
                    date = italy.getDate();
                    passengerDetails();
                }
                case 4 -> {
                    Spain spain = new Spain("Spain");
                    spain.getTravellingOptions();
                    price = spain.getPrice();
                    date = spain.getDate();
                    passengerDetails();
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> {
                    System.err.println("Please provide a number between 1 and 5");
                    System.out.println("=======================================================");
                }
            }
        }
    }

    private void passengerDetails() throws IOException {
        System.out.println("How many tickets?");
        int numberOfTickets = getInt();
        if (numberOfTickets <= 0) {
            System.out.println("Wrong number of tickets! Minimum value is 1!");
            passengerDetails();
        } else {
            double totalPriceEUR = numberOfTickets * price;

            System.out.printf("You've chosen to book %d tickets. Your price will be " + totalPriceEUR + euro +
                    "\nPlease enter name for a booking person. ", numberOfTickets);
            String name = getString();
            System.out.println("Fetching the latest currency rates...");
            double totalPriceConvertedToSEK = Converter.convertEURtoSEK(euro, krona, totalPriceEUR);
            System.out.printf("All right %s! We've got the booking prepared and ready!%n" +
                    "This is a summary of Your booking:%n" +
                    "Destination: " + Country.getName() +
                    " for %d passengers" +
                    " on the " + dateFormatter(date) +
                    " for a total of: " + displayCurrency(totalPriceConvertedToSEK) + "\n", name, numberOfTickets);

            saveToFile(totalPriceConvertedToSEK, name, numberOfTickets);
        }
        System.exit(0);
    }

    void saveToFile(double price, String name, int nrTickets) throws IOException {
        File ticketFile = new File("ticket.txt");
        try (PrintWriter writer = new PrintWriter(ticketFile)) {
            writer.print("This is a summary of Your booking:\n" +
                    "The booking is issued for: " + name +
                    "\nDestination: " + Country.getName() +
                    " for " + nrTickets + " passengers" +
                    " on the " + dateFormatter(date) +
                    " for a total of: " + Math.round(price) + "kr");
            System.out.println("Your booking was saved to file: " + ticketFile.getAbsolutePath());
        } catch (IOException e) {
            throw new IOException("Couldn't write to file");
        }
    }

    public String dateFormatter(LocalDateTime date) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(date);
    }

    private String displayCurrency(double price) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(price);
    }

    private String getString() {
        Scanner s = new Scanner(System.in);
        System.out.println("Type here: ");
        String string;
        try {
            string = s.next();
            if (string != null) {
                return string;
            } else {
                System.out.println("NPE! Please type your answer: ");
                string = s.next();
            }
            s.close();
        } catch (InputMismatchException e) {
            return "Incorrect answer";
        }
        return string;
    }

    private int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please type your choice here: ");
        while (true) {
            try {
                return s.nextInt();
            } catch (InputMismatchException e) {
                s.nextLine();
                System.err.println("Incorrect input! Type in a positive number");
            }
        }
    }

    BookingSystem() throws IOException {
        menu();
    }

    public static void main(String[] args) throws IOException {
        new BookingSystem();
    }
}

