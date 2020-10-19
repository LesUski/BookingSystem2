package com.BokingSystem;

import java.time.LocalDateTime;

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
        return "No." + number + ", date: " + date + ", price: " + price + "SEK";
    }
}
