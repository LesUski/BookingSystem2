package com.BokingSystem;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class BookingSystemTest {

    public static Currency euro = Currency.getInstance("EUR");
    public static Currency krona = Currency.getInstance("SEK");
    private static final double TEST_DELTA = 0.9;

    @org.junit.jupiter.api.Test
    void chosenCountry() {
    }

    @org.junit.jupiter.api.Test
    void bookATicket() {
    }

    @org.junit.jupiter.api.Test
    void passengerDetails() {
    }

//    @Test
//    void testRateConnectionStatus() throws IOException {
//        int statusExpected = 200;
//        int statusActual = BookingSystem.rate(euro,krona);
//        assertEquals(statusExpected, statusActual);
//    }

    @Test
    void testRate() throws IOException {

        double expected = 10.341576;
        double actual = BookingSystem.rate(euro, krona);
        assertEquals(expected, actual, TEST_DELTA);
    }

    @org.junit.jupiter.api.Test
    void convertPrice() {
    }

}