package com.BokingSystem;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AustriaTest {

    @Test
    void checkIfConvertToArrayThrowsException() {
        File file = new File("src/com/BokingSystem/DateFiles/austriaDatesFlight.tx");
        Austria austriaTest = new Austria("AustriaTest");
        assertThrows(FileNotFoundException.class, () -> austriaTest.convertToArray(file));
    }
}
