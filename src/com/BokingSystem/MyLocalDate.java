package com.BokingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyLocalDate {
    int number;
    LocalDateTime date;
    double price;

    public MyLocalDate(int number, LocalDateTime date, double price) {
        this.number = number;
        this.date = date;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String strDate = DateTimeFormatter.ISO_LOCAL_DATE.format(date);
        return "No." + number + ", date: " +  strDate + ", price: " + price + "EUR";
    }
}
