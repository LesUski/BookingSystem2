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
    Locale currentLocale = new Locale("de", "DE");
    Currency c = Currency.getInstance(currentLocale);

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

        MyLocalDate italyDate1 = new MyLocalDate(1, dates[0], 69);
        MyLocalDate italyDate2 = new MyLocalDate(2, dates[1], 99);
        MyLocalDate italyDate3 = new MyLocalDate(3, dates[2], 59);

        ArrayList<MyLocalDate> italyList = new ArrayList<MyLocalDate>();
        italyList.add(italyDate1);
        italyList.add(italyDate2);
        italyList.add(italyDate3);
        System.out.println("List of available dates and prices:");
        Collections.sort(italyList, (o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));

        for(MyLocalDate date: italyList) {
            System.out.println(date.toString());
        }
        chooseDate(italyList);
    }

    @Override
    public void chooseDate(ArrayList<MyLocalDate> listDate){
        Scanner sc = new Scanner(System.in);

        System.out.print("Would you like to choose one of those days? Type the number here:");
        int input = sc.nextInt();

        if (input == 1) {
            listDate.stream().filter(myDate -> myDate.getNumber() == 1);
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 1).forEach(System.out::println);
            price = listDate.get(1).getPrice();
            date = listDate.get(1).getDate();
            System.out.println(listDate.get(1).getPrice());

        } else if (input == 2) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 2).forEach(System.out::println);
            price = listDate.get(2).getPrice();
            date = listDate.get(2).getDate();
            System.out.println(listDate.get(2).getPrice());

        } else if (input == 3) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 3).forEach(System.out::println);
            price = listDate.get(0).getPrice();
            date = listDate.get(0).getDate();
            System.out.println(listDate.get(0).getPrice());

        }
    }
}
