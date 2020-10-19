package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Estonia extends Country{
    public double price = 0;
    public LocalDateTime date;

    public Estonia(String name) {
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
    public void getDates(File file) {
        try {
            System.out.println("Those are the available dates: ");
            Scanner scn = new Scanner(file);
            String[] dateStrings = scn.nextLine().split(", ");
            LocalDateTime[] dates = new LocalDateTime[dateStrings.length];

            for (int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                System.out.println("[" + (i+1) + "] " +  dates[i]);
            }
            chooseDate(dates);
        } catch (DateTimeParseException e) {
            System.out.println("Could not read file ");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + file);
        }
    }

    @Override
    public void getTravellingOptions() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Those are available travelling options to " + getName() + ":\n" +
                "[1] Flight\n" +
                "[2] Ferry\n" +
                "[3] Bus ");
        System.out.print("Type your choice here: ");
        int input = sc.nextInt();
        if (input == 1) {
            File file = new File("src/com/BokingSystem/DateFiles/estoniaDatesFlight.txt");
            getDates(file);
        }
        if (input == 2) {
            File file = new File("src/com/BokingSystem/DateFiles/estoniaDatesFerry.txt");
            getDates(file);
        }
        if (input == 3) {
            File file = new File("src/com/BokingSystem/DateFiles/estoniaDatesBus.txt");
            getDates(file);
        }
        return;
    }

    @Override
    public double getPriceForSpecificDate(LocalDateTime date) {
        if(date.getDayOfWeek().getValue() == 1) {
            price = 120;
            return 120;
        } else if (date.getDayOfWeek().getValue() == 5) {
            price = 135;
            return 135;
        } else if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) {
            price = 142;
            return 142;
        } else
            price = 125;
        return 125;
    }

    @Override
    public void chooseDate(LocalDateTime[] dates) {
        System.out.print("Type one of the above options from the list here: ");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        if(input == 1) {
            price = getPriceForSpecificDate(dates[0]);
            this.date = dates[0];
            System.out.println(getPriceForSpecificDate(dates[0]) + " SEK");
        } else if(input == 2) {
            price = getPriceForSpecificDate(dates[1]);
            this.date = dates[1];
            System.out.println(getPriceForSpecificDate(dates[1]) + " SEK");
        } else if (input == 3) {
            price = getPriceForSpecificDate(dates[2]);
            this.date = dates[2];
            System.out.println(getPriceForSpecificDate(dates[2]) + " SEK");
        }
    }
}
