package com.BokingSystem;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class BookingSystemTest {

    public static Currency euro = Currency.getInstance("EUR");
    public static Currency krona = Currency.getInstance("SEK");
    private static final double TEST_DELTA = 0.9;

    @Test
    void checkIfConvertToArrayThrowsException() {
        File file = new File("src/com/BokingSystem/DateFiles/austriaDatesFlight.tx");
        Austria austriaTest = new Austria("AustriaTest");
        assertThrows(FileNotFoundException.class, () -> austriaTest.convertToArray(file));
    }

    @Test
    void checkFormatFromDateFormatter() {
        LocalDateTime date = LocalDateTime.parse("2021-03-04 06:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        assertEquals("2021-03-04", BookingSystem.dateFormatter(date));
    }

    @Test
    void testGetRateFromURL() {
        double expected = 10.341576;
        double actual = BookingSystem.getRateFromURL(euro, krona);
        assertEquals(expected, actual, TEST_DELTA);
    }
    

    @Test
    void testConvertRateEUR_SEK() {
        double price = 10;
        assertEquals(103.4, BookingSystem.convertRateEUR_SEK(price), TEST_DELTA);
    }

//    @Test
//    void checkExceptionInSaveToFile() {
//        
//    }

//    @Test
//    void testRateConnectionStatus() throws IOException {
//        int statusExpected = 200;
//        int statusActual = BookingSystem.rate(euro,krona);
//        assertEquals(statusExpected, statusActual);
//    }
//    @Test
//    void checkExceptionInGetRateFromURL() {
//    assertThrows(IOException.class, () -> BookingSystem.getRateFromURL(euro, e));
//    }

}