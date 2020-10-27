package com.BokingSystem;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

class BookingSystemTest {

    @Test
    void checkFormatFromDateFormatter() throws IOException {
        BookingSystem bookingSystem = new BookingSystem();
        LocalDateTime date = LocalDateTime.parse("2021-03-04 06:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        assertEquals("2021-03-04", bookingSystem.dateFormatter(date));
    }

    @Test
    void checkExceptionInSaveToFile() throws IOException {
        BookingSystem bookingSystem = new BookingSystem();
        assertThrows(NullPointerException.class, () -> bookingSystem.saveToFile(10,"Name",1));
    }
}