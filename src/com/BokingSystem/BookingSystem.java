package com.BokingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class BookingSystem {
    public static Scanner sc = new Scanner(System.in);
    private static double price;
    private static LocalDateTime date;
    public static final String CURRENCY_CONVERTER_API_API_KEY = "bc90f424957001330a57";
    private static HttpURLConnection connection;
    public static Currency euro = Currency.getInstance("EUR");
    public static Currency krona = Currency.getInstance("SEK");
    static Locale currentLocale = new Locale("sv", "SE");


    public void menu() throws IOException {
        System.out.println("\n\t\tWelcome to Booking System!\n" +
                "=======================================================\n" +
                "Please choose your option:");
        System.out.println("Choose [1] To choose country of destination.\n" +
                "Choose [2] To choose the cheapest tickets (last minute)\n" +
                "Choose [3] To exit\n" +
                "=======================================================");
        System.out.print("Type here: ");
        int input = sc.nextInt();
        switch (input) {
            case 1:
                chosenCountry();
            case 2:
                //getCheapestTickets();
            case 3:
                break;
        }
    }

    public static void chosenCountry() throws IOException {
        System.out.println("List of available countries: [1]Austria, [2]Denmark, [3]Estonia, [4]Finland, [5]France, [6]Germany, [7]Italy, [8]Spain\n" +
                "press [0] to exit");
        System.out.print("Choose country: ");
        int input = sc.nextInt();

        switch (input) {
            case 0:
                System.exit(0);
            case 1:
                Austria austria = new Austria("Austria");
                austria.getTravellingOptions();
                price = austria.getPrice();
                date = austria.getDate();
                bookATicket();
                break;
            case 2:
                Denmark denmark = new Denmark("Denmark");
                denmark.getTravellingOptions();
                price = denmark.getPrice();
                date = denmark.getDate();
                bookATicket();
                break;
            case 3:
                Estonia estonia = new Estonia("Estonia");
                estonia.getTravellingOptions();
                price = estonia.getPrice();
                date = estonia.getDate();
                bookATicket();
                break;
            case 4:
                Finland finland = new Finland("Finland");
                finland.getTravellingOptions();
                price = finland.getPrice();
                date = finland.getDate();
                bookATicket();
                break;
            case 5:
                France france = new France("France");
                france.getTravellingOptions();
                price = france.getPrice();
                date = france.getDate();
                bookATicket();
                break;
            case 6:
                Germany germany = new Germany("Germany");
                germany.getTravellingOptions();
                price = germany.getPrice();
                date = germany.getDate();
                bookATicket();
                break;
            case 7:
                Italy italy = new Italy("Italy");
                italy.getTravellingOptions();
                price = italy.getPrice();
                date = italy.getDate();
                bookATicket();
                break;
            case 8:
                Spain spain = new Spain("Spain");
                spain.getTravellingOptions();
                price = spain.getPrice();
                date = spain.getDate();
                bookATicket();
                break;
            default:
                chosenCountry();
        }
    }

    public static void bookATicket() throws IOException {
        System.out.println("Would you like to book that ticket?");
        System.out.println("Press [y] to print ticket or [b] to change date or anything else to exit");
        System.out.print("Type here:");
        String input = sc.next();
        if (input.equals("y")) {
            passengerDetails();
            System.out.println("");

        } else if (input.equals("b")) {
            return;
        } else System.exit(0);
    }

    public static void passengerDetails() throws IOException {
        System.out.println("How many tickets?");
        int input = sc.nextInt();
        double totalPrice = input * price;
        System.out.println("You've chosen to book " + input + " tickets. Your price will be " + totalPrice + euro);
        System.out.println("Enter name for a booking person: ");
        String name = sc.next();
        System.out.println("Fetching the latest exchange rate to your currency...");
        double finalPriceInSEK = input*convertRateEUR_SEK(price);
        System.out.println("All right " + name + "! We've got the booking prepared and ready! Press [p] to print ticket");
        if(sc.next().equals("p")) {
            System.out.println("This is a summary of Your booking:\n" +
                    "Destination: " + Country.getName() +
                    " for " + input + " passengers" +
                    " on the " + date +
                    " for a total of: " + displayCurrency(currentLocale, finalPriceInSEK));
        }
    }

    static double rate(Currency from, Currency to) throws IOException{

        String API_KEY = "bc90f424957001330a57";
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static double convertRateEUR_SEK(double price) throws IOException {

        return price*(rate(euro, krona));
    }

    static String displayCurrency(Locale currentLocale, double price) {
        Currency currentCurrency = Currency.getInstance(currentLocale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(price);
    }

    public BookingSystem() throws IOException {
        menu();
    }

    public static void main(String[] args) throws IOException {
        new BookingSystem();
    }
}
