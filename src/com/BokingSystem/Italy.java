package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Italy extends Country {
    public double price = 0;
    public LocalDateTime date;
    private double lowestPrice;
    Locale currentLocale = new Locale("de", "DE");
    Currency c = Currency.getInstance(currentLocale);
    ArrayList<MyLocalDate> italyList = new ArrayList<MyLocalDate>();

    public Italy(String name) {
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
                File file = new File("src/com/BokingSystem/DateFiles/italyDatesFlight.txt");
                getDates(file);
            }
            case 2 -> {
                File file2 = new File("src/com/BokingSystem/DateFiles/italyDatesTrain.txt");
                getDates(file2);
            }
            case 3 -> {
                File file3 = new File("src/com/BokingSystem/DateFiles/italyDatesBus.txt");
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

        italyList.add(new MyLocalDate(1, dates[0], 69));
        italyList.add(new MyLocalDate(2, dates[1], 99));
        italyList.add(new MyLocalDate(3, dates[2], 59));

        System.out.println("List of available dates and prices:");
        Collections.sort(italyList, (o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));

        for(MyLocalDate date: italyList) {
            System.out.println(date.toString());
        }
        chooseDate(italyList);
    }

    @Override
    public void chooseDate(ArrayList<MyLocalDate> listDate){
        System.out.print("Would you like to choose one of those days? ");
        int input = getAnswer();

        switch (input) {
            case 1 -> {
                System.out.print("You've chosen the ");
                listDate.stream().filter(myDate -> myDate.getNumber() == 1).forEach(System.out::println);
                price = listDate.get(1).getPrice();
                date = listDate.get(1).getDate();
            }
            case 2 -> {
                System.out.print("You've chosen the ");
                listDate.stream().filter(myDate -> myDate.getNumber() == 2).forEach(System.out::println);
                price = listDate.get(2).getPrice();
                date = listDate.get(2).getDate();
            }
            case 3 -> {
                System.out.print("You've chosen the ");
                listDate.stream().filter(myDate -> myDate.getNumber() == 3).forEach(System.out::println);
                price = listDate.get(0).getPrice();
                date = listDate.get(0).getDate();
            }
            default -> {
                System.err.println("Please enter a number between 1 and 3");
                chooseDate(italyList);
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
        Collections.sort(italyList, (o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));

        return lowestPrice = italyList.get(0).getPrice();
    }
}
