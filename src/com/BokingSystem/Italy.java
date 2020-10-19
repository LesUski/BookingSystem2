package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Italy extends Country {
    public double price = 0;
    public LocalDateTime date;

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Those are available travelling options to " + getName() + ":\n" +
                "[1] Flight\n" +
                "[2] Train\n" +
                "[3] Bus ");
        System.out.print("Type your choice here: ");
        int input = sc.nextInt();
        if (input == 1) {
            File file = new File("src/com/BokingSystem/DateFiles/italyDatesFlight.txt");
            getDates(file);
        }
        if (input == 2) {
            File file = new File("src/com/BokingSystem/DateFiles/italyDatesTrain.txt");
            getDates(file);
        }
        if (input == 3) {
            File file = new File("src/com/BokingSystem/DateFiles/italyDatesBus.txt");
            getDates(file);
        }
        return;
    }

    @Override
    public void getDates(File file) {
        String[] dateStrings = new String[3];
        LocalDateTime[] dates = new LocalDateTime[dateStrings.length];

        try {
            //System.out.println("Those are the available dates: ");
            Scanner scn = new Scanner(file);
            dateStrings = scn.nextLine().split(", ");

            dates = new LocalDateTime[dateStrings.length];

            for (int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                //System.out.println("[" + (i+1) + "] " +  dates[i]);
            }

        } catch (DateTimeParseException e) {
            System.out.println("Could not read file ");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + file);
        }

        MyLocalDate italyDate1 = new MyLocalDate(1, dates[0], 199);
        MyLocalDate italyDate2 = new MyLocalDate(2, dates[1], 99);
        MyLocalDate italyDate3 = new MyLocalDate(3, dates[2], 299);

        ArrayList<MyLocalDate> italyList = new ArrayList<MyLocalDate>();
        italyList.add(italyDate1);
        italyList.add(italyDate2);
        italyList.add(italyDate3);
        System.out.println("List of available dates and prices:");
        Collections.sort(italyList, (o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));

        for(MyLocalDate date: italyList) {
            System.out.println(date.toString());
        }
        int chosenDate = chooseDate(italyList);
        if (chosenDate == 1) {
            this.price = italyDate1.getPrice();
            this.date = italyDate1.getDate();
        } else if (chosenDate == 2) {
            this.price = italyDate2.getPrice();
            this.date = italyDate2.getDate();
        } else if (chosenDate == 3) {
            this.price = italyDate3.getPrice();
            this.date = italyDate3.getDate();
        }
    }

    public int chooseDate(ArrayList<MyLocalDate> listDate){
        Scanner sc = new Scanner(System.in);

        System.out.print("Would you like to choose one of those days? Type the number here:");
        int input = sc.nextInt();

        if (input == 1) {
            listDate.stream().filter(myDate -> myDate.getNumber() == 1);
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 1).forEach(System.out::println);
//            price = listDate.get(input-1).getPrice();
//            System.out.println(listDate.get(input-1).getPrice());
            return input;
        } else if (input == 2) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 2).forEach(System.out::println);
//            price = listDate.get(input-1).getPrice();
//            System.out.println(listDate.get(input-1).getPrice());
            return input;
        } else if (input == 3) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 3).forEach(System.out::println);
//            price = listDate.get(input-1).getPrice();
//            System.out.println(listDate.get(input-1).getPrice());
            return input;
        } else if (input == 4) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 4).forEach(System.out::println);
//            price = listDate.get(input-1).getPrice();
//            System.out.println(listDate.get(input-1).getPrice());
            return input;
        }
        return 0;
    }
    /*-------------------------------------------------------------
                        Unused methods
    ---------------------------------------------------------------
     */


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
