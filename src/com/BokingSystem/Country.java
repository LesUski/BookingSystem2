package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Country {
    private static String name;
    private double price;
    private static LocalDateTime date;
    String[] dateStrings = new String[3];
    LocalDateTime[] dates = new LocalDateTime[dateStrings.length];

    public Country(String name) {
        this.price = price;
        this.name = name;
    }

    public static String getName() {
        return name;
    }
    public double getPrice() {
        return this.price;
    }
    public LocalDateTime getDate(){
        return date;
    }

    public abstract void getDate(LocalDateTime[] array);
    public abstract LocalDateTime[] convertToArray(File file);
    public abstract void getTravellingOptions();
    public abstract void chooseDate(ArrayList<MyLocalDate> listDate);

    public int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter your choice's number: ");
        while (true) {
            try {
                return s.nextInt();
            } catch (InputMismatchException e) {
                s.nextLine();
                System.err.println("Incorrect input! Type in a positive number: ");
            }
        }
    }
}
