package com.BokingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Country {

    private static String name;
    public double price;
    public LocalDateTime date;
    String[] dateStrings = new String[3];
    LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
    ArrayList<MyLocalDate> infoList = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

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

    public static String getName() {
        return name;
    }

    public abstract double getPrice();

    public abstract LocalDateTime getDate();

    public abstract void getDate(LocalDateTime[] array);

    public abstract LocalDateTime[] convertToArray(File file) throws FileNotFoundException;

    public abstract void getTravellingOptions() throws FileNotFoundException;

    public abstract void chooseDate(ArrayList<MyLocalDate> listDate);


}
