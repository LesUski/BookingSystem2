package com.BokingSystem;

import java.util.Comparator;

public class Date {
    int number;
    String date;
    double price;

    public Date(int number, String date, double price) {
        this.number = number;
        this.date = date;
        this.price = price;
    }

    public static Comparator<Date> DatePriceComparator = new Comparator<Date>() {
        public int compare (Date d1, Date d2) {
            int price1 = (int) d1.getPrice();
            int price2 = (int) d2.getPrice();

            return price2-price1;
        }
    };

    @Override
    public String toString() {
        return "No." + number + ", date: " + date + ", price: " + price + "SEK";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
