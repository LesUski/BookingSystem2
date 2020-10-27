package com.BokingSystem;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingSystem {
    private static double price;
    private static LocalDateTime date;
    private static final Currency euro = Currency.getInstance("EUR");
    private static final Currency krona = Currency.getInstance("SEK");
    private static final Locale currentLocale = new Locale("sv", "SE");

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

    private static void chosenCountry() throws IOException {
        System.out.println("List of available countries: [1]Austria, [2]Germany, [3]Italy, [4]Spain\n" +
                "press [5] to exit");

        while(true) {
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

    private static void passengerDetails() throws IOException {
        System.out.println("How many tickets?");
        int numberOfTickets = getInt();
        if (numberOfTickets <= 0) {
            System.out.println("Wrong number of tickets! Minimum value is 1!");
            passengerDetails();
        } else {
            double totalPrice = numberOfTickets * price;

            System.out.printf("You've chosen to book %d tickets. Your price will be " + totalPrice + euro +
                       "\nPlease enter name for a booking person. ", numberOfTickets);
            String name = getString();

            System.out.printf("All right %s! We've got the booking prepared and ready!%n" +
                        "This is a summary of Your booking:%n" +
                        "Destination: " + Country.getName() +
                        " for %d passengers" +
                        " on the " + dateFormatter(date) +
                        " for a total of: " + totalPrice + "EUR\n", name, numberOfTickets);

            getPriceInSEK(totalPrice);

            saveToFile(totalPrice, name, numberOfTickets);
        }
        System.exit(0);
    }

    static double getPriceInSEK(double price) {
        System.out.println("Would like to see the price in your currency? Press [y] for yes or anything else to finish");
        double priceInEuro = price*getRateFromURL(euro, krona);
        if (getString().equals("y")) {
            System.out.println("Your final price is: " + priceInEuro + "EUR");
        } else {
            System.out.println("Thank you! Have a good trip!");
        }
        return priceInEuro;
    }

    /**
     * This method fetches the current currency ratio from API's URL address.
     */
    public static double getRateFromURL(Currency from, Currency to){
        String API_KEY = "pr_2905d826378644f3b0034f291637c330";
        String USER_AGENT_ID = "Java/"
                + System.getProperty("java.version");
        try {
            String queryPath
                    = "https://free.currconv.com/api/v7/convert?q="
                    + from.getCurrencyCode() + "_"
                    + to.getCurrencyCode()
                    + "&compact=ultra&apiKey=" + API_KEY;
            URL queryURL = new URL(queryPath);

            HttpURLConnection connection = (HttpURLConnection) queryURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT_ID);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status == 200) { // 200 is HTTP status OK
                InputStream stream
                        = (InputStream) connection.getContent();
                Scanner scanner = new Scanner(stream);
                String quote = scanner.nextLine();
                String number = quote.substring(quote.indexOf(':') + 1,
                        quote.indexOf('}'));
                return Double.parseDouble(number);
            } else {
                String excMsg = "Query " + queryPath
                        + " returned status " + status;
                throw new RuntimeException(excMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    static void saveToFile(double price, String name, int nrTickets) throws IOException {
        File ticketFile = new File("ticket.txt");
        try (PrintWriter writer = new PrintWriter(ticketFile)) {
            writer.print("This is a summary of Your booking:\n" +
                    "The booking is issued for: " + name +
                    "\nDestination: " + Country.getName() +
                    " for " + nrTickets + " passengers" +
                    " on the " + dateFormatter(date) +
                    " for a total of: " + displayCurrency(price));
            System.out.println("Your booking was saved to file: " + ticketFile.getAbsolutePath());
        } catch (IOException e) {
            throw new IOException("Couldn't write to file");
        }
    }

    public static String dateFormatter(LocalDateTime date) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(date);
    }

    public static double convertRateEUR_SEK(double price) {
        return price*(getRateFromURL(euro, krona));
    }

    private static String displayCurrency(double price) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(BookingSystem.currentLocale);
        return currencyFormatter.format(price);
    }

    private static String getString() {
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

    static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please type your choice here: ");
        while(true) {
            try {
                return s.nextInt();
            } catch (InputMismatchException e) {
                s.nextLine();
                System.err.println("Incorrect input! Type in a positive number");
            }
        }
    }

    private BookingSystem() throws IOException {
        menu();
    }

    public static void main(String[] args) throws IOException {
        new BookingSystem();
    }
}

