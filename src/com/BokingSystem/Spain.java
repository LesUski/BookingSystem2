package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Spain extends Country {

    public double price = 0;
    public LocalDateTime date;

    public Spain(String name) {
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
    public void getTravellingOptions(){

        ArrayList<Date> dateList = new ArrayList<>();
        dateList.add(new Date(1,"2021-06-03", 65));
        dateList.add(new Date(2,"2021-07-03", 21));
        dateList.add(new Date(3,"2021-08-03", 51));
        dateList.add(new Date(4,"2021-09-03", 10));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("List of available dates and prices:");

        Collections.sort(dateList, (o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));

        for(Date date: dateList) {
            System.out.println(date.toString());
        }
        chooseDate(dateList);
    }

    public void chooseDate(ArrayList<Date> listDate){

        Scanner sc = new Scanner(System.in);

        System.out.print("Would you like to choose one of those days? Type the number here:");
        int input = sc.nextInt();

        if (input == 1) {
            listDate.stream().filter(myDate -> myDate.getNumber() == 1);
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 1).forEach(System.out::println);
            price = listDate.get(3).getPrice();
            System.out.println(listDate.get(3).getPrice());

        } else if (input == 2) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 2).forEach(System.out::println);
            price = listDate.get(1).getPrice();
            System.out.println(listDate.get(1).getPrice());

        } else if (input == 3) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 3).forEach(System.out::println);
            price = listDate.get(2).getPrice();
            System.out.println(listDate.get(2).getPrice());

        } else if (input == 4) {
            System.out.print("You've chosen the ");
            listDate.stream().filter(myDate -> myDate.getNumber() == 4).forEach(System.out::println);
            price = listDate.get(0).getPrice();
            System.out.println(listDate.get(0).getPrice());

        }
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
    /*-------------------------------------------------------------
                        Unused methods
    ---------------------------------------------------------------
     */

    @Override
    public double getPriceForSpecificDate(LocalDateTime date) {
        return 0.0;
    }

    @Override
    public void chooseDate(LocalDateTime[] dates) {

    }

}
