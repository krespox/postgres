package com.postgres.model;

import com.postgres.util.FileLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonValidatorTest {

    @Test
    public void testIsValid() {
        Person validPerson = new Person();
        validPerson.setFirstName("John");
        validPerson.setLastName("Doe");

        boolean isValid = PersonValidator.isValid(validPerson);
        FileLogger.logTestResult("testIsValid (valid person): " + isValid);
        assertTrue(isValid);

        Person invalidPersonSameName = new Person();
        invalidPersonSameName.setFirstName("John");
        invalidPersonSameName.setLastName("John");

        boolean isInvalidSameName = !PersonValidator.isValid(invalidPersonSameName);
        FileLogger.logTestResult("testIsValid (same name): " + isInvalidSameName);
        assertTrue(isInvalidSameName);

        Person invalidPersonWithSpace = new Person();
        invalidPersonWithSpace.setFirstName("John");
        invalidPersonWithSpace.setLastName("Doe Smith");

        boolean isInvalidWithSpace = !PersonValidator.isValid(invalidPersonWithSpace);
        FileLogger.logTestResult("testIsValid (with space): " + isInvalidWithSpace);
        assertTrue(isInvalidWithSpace);

        Person invalidPersonLowerCase = new Person();
        invalidPersonLowerCase.setFirstName("john");
        invalidPersonLowerCase.setLastName("Doe");

        boolean isInvalidLowerCase = !PersonValidator.isValid(invalidPersonLowerCase);
        FileLogger.logTestResult("testIsValid (lower case): " + isInvalidLowerCase);
        assertTrue(isInvalidLowerCase);
    }
}
