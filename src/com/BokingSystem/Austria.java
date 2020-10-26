package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Austria extends Country {
    private boolean quit = true;

    Austria(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public void getTravellingOptions() throws FileNotFoundException {
        System.out.println("Those are available travelling options to " + getName() + ":\n" +
                "[1] Flight\n" +
                "[2] Train\n" +
                "[3] Bus ");
        while (quit) {
            switch (getInt()) {
                case 1 -> getDate(convertToArray(new File("src/com/BokingSystem/DateFiles/austriaDatesFlight.txt")));
                case 2 -> getDate(convertToArray(new File("src/com/BokingSystem/DateFiles/austriaDatesTrain.txt")));
                case 3 -> getDate(convertToArray(new File("src/com/BokingSystem/DateFiles/austriaDatesBus.txt")));
                default -> System.err.println("Please provide a number between 1 and 3");
            }
        }
    }

    @Override
    public LocalDateTime[] convertToArray(File file) throws FileNotFoundException {
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
            throw new FileNotFoundException("Could not find file " + file);
        }
        return dates;
    }

    @Override
    public void getDate(LocalDateTime[] dates) {
        infoList.add(new MyLocalDate(1, dates[0], 109));
        infoList.add(new MyLocalDate(2, dates[1], 59));
        infoList.add(new MyLocalDate(3, dates[2], 29));

        System.out.println("List of available dates and prices:");
        infoList.sort((o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));

        for (MyLocalDate date : infoList) {
            System.out.println(date.toString());
        }
        chooseDate(infoList);
    }

    @Override
    public void chooseDate(ArrayList<MyLocalDate> listDate) {
        System.out.print("Would you like to choose one of those days? ");

        while (quit) {
            switch (getInt()) {
                case 1 -> {
                    System.out.print("You've chosen the ");
                    listDate.stream().filter(myDate -> myDate.getNumber() == 1).forEach(System.out::println);
                    price = listDate.get(2).getPrice();
                    date = listDate.get(2).getDate();
                    quit = false;
                }
                case 2 -> {
                    System.out.print("You've chosen the ");
                    listDate.stream().filter(myDate -> myDate.getNumber() == 2).forEach(System.out::println);
                    price = listDate.get(1).getPrice();
                    date = listDate.get(1).getDate();
                    quit = false;
                }
                case 3 -> {
                    System.out.print("You've chosen the ");
                    listDate.stream().filter(myDate -> myDate.getNumber() == 3).forEach(System.out::println);
                    price = listDate.get(0).getPrice();
                    date = listDate.get(0).getDate();
                    quit = false;
                }
                default -> {
                    System.err.println("Please enter a number between 1 and 3");
                    chooseDate(infoList);
                }
            }
        }
    }
}
