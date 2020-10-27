package com.BokingSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterTest {
    private static final double TEST_DELTA = 0.9;
    private static final Currency euro = Currency.getInstance("EUR");
    private static final Currency krona = Currency.getInstance("SEK");

    @Test
    @DisplayName("Test if the connection to API is possible")
    void getURLStatus() {
        assertEquals(200, Converter.getURLStatus());
    }

    @Test
    @DisplayName("Checks if the conversion rate lies in the declared delta for provided value")
    void convertEURtoSEK() {
        assertEquals(309, Converter.convertEURtoSEK(euro, krona, 30), TEST_DELTA);
    }

}