package com.BokingSystem;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Country {
    private static String name;
    private double price;
    private static LocalDateTime date;

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

    public abstract void getDates(File file);
    public abstract void getTravellingOptions();
    public abstract void chooseDate(ArrayList<MyLocalDate> listDate);


}
