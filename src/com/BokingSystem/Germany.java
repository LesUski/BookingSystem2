package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Germany extends Country {
    public double price = 0;
    public LocalDateTime date;
    private double lowestPrice;
    Locale currentLocale = new Locale("de", "DE");
    Currency c = Currency.getInstance(currentLocale);
    ArrayList<MyLocalDate> germanyList = new ArrayList<MyLocalDate>();

    public Germany(String name) {
        super(name);
        this.price = price;
        this.date = date;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public void getTravellingOptions() {
        System.out.println("Those are available travelling options to " + getName() + ":\n" +
                "[1] Flight\n" +
                "[2] Train\n" +
                "[3] Bus ");

        int input = getAnswer();

        switch (input) {
            case 1 -> {
                File file = new File("src/com/BokingSystem/DateFiles/germanyDatesFlight.txt");
                getDates(file);
            }
            case 2 -> {
                File file2 = new File("src/com/BokingSystem/DateFiles/germanyDatesTrain.txt");
                getDates(file2);
            }
            case 3 -> {
                File file3 = new File("src/com/BokingSystem/DateFiles/germanyDatesBus.txt");
                getDates(file3);
            }
            default -> {
                System.err.println("Please provide a number between 1 and 3");
                getTravellingOptions();
            }
        }
    }

    @Override
    public void getDates(File file) {
        String[] dateStrings = new String[3];
        LocalDateTime[] dates = new LocalDateTime[dateStrings.length];

        try {
            Scanner scn = new Scanner(file);
            dateStrings = scn.nextLine().split(", ");

            dates = new LocalDateTime[dateStrings.length];

            for (int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }

        } catch (DateTimeParseException e) {
            System.out.println("Could not read file ");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + file);
        }

        germanyList.add(new MyLocalDate(1, dates[0], 44));
        germanyList.add(new MyLocalDate(2, dates[1], 32));
        germanyList.add(new MyLocalDate(3, dates[2], 35));

        System.out.println("List of available dates and prices:");
        Collections.sort(germanyList, (o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));

        for(MyLocalDate date: germanyList) {
            System.out.println(date.toString());
        }
        chooseDate(germanyList);
    }

    @Override
    public void chooseDate(ArrayList<MyLocalDate> listDate) {
        System.out.print("Would you like to choose one of those days? ");

        int input = getAnswer();

        switch (input) {
            case 1 -> {
                System.out.print("You've chosen the ");
                listDate.stream().filter(myDate -> myDate.getNumber() == 1).forEach(System.out::println);
                price = listDate.get(2).getPrice();
                date = listDate.get(2).getDate();
            }
            case 2 -> {
                System.out.print("You've chosen the ");
                listDate.stream().filter(myDate -> myDate.getNumber() == 2).forEach(System.out::println);
                price = listDate.get(0).getPrice();
                date = listDate.get(0).getDate();
            }
            case 3 -> {
                System.out.print("You've chosen the ");
                listDate.stream().filter(myDate -> myDate.getNumber() == 3).forEach(System.out::println);
                price = listDate.get(1).getPrice();
                date = listDate.get(1).getDate();
            }
            default -> {
                System.out.println("Please provide a number between 1 and 3");
                chooseDate(germanyList);
            }
        }
    }

    private static int getAnswer() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter your choice's number");
        while(true) {
            try {
                return s.nextInt();
            } catch (InputMismatchException e) {
                s.nextLine();
                System.err.println("Incorrect input! Type in a positive number: ");
            }
        }
    }

    @Override
    public double getCheapestTickets() {
        Collections.sort(germanyList, (o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));

        return lowestPrice = germanyList.get(0).getPrice();
    }
}
