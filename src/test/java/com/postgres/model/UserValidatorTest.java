package com.postgres.model;

import com.postgres.util.FileLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    @Test
    public void testIsValid() {
        User validUser = new User();
        validUser.setFirstName("John");
        validUser.setLastName("Doe");

        boolean isValid = UserValidator.isValid(validUser);
        FileLogger.logTestResult("testIsValid (valid person): " + isValid);
        assertTrue(isValid);

        User invalidUserSameName = new User();
        invalidUserSameName.setFirstName("John");
        invalidUserSameName.setLastName("John");

        boolean isInvalidSameName = !UserValidator.isValid(invalidUserSameName);
        FileLogger.logTestResult("testIsValid (same name): " + isInvalidSameName);
        assertTrue(isInvalidSameName);

        User invalidUserWithSpace = new User();
        invalidUserWithSpace.setFirstName("John");
        invalidUserWithSpace.setLastName("Doe Smith");

        boolean isInvalidWithSpace = !UserValidator.isValid(invalidUserWithSpace);
        FileLogger.logTestResult("testIsValid (with space): " + isInvalidWithSpace);
        assertTrue(isInvalidWithSpace);

        User invalidUserLowerCase = new User();
        invalidUserLowerCase.setFirstName("john");
        invalidUserLowerCase.setLastName("Doe");

        boolean isInvalidLowerCase = !UserValidator.isValid(invalidUserLowerCase);
        FileLogger.logTestResult("testIsValid (lower case): " + isInvalidLowerCase);
        assertTrue(isInvalidLowerCase);
    }
}
