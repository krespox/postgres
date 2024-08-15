package com.postgres.model;

public class UserValidator {

    public static boolean isValid(User user) {
        if (user == null) return false;

        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        boolean isValidFirstName = firstName.matches("^[A-Z][a-z]*$");
        boolean isValidLastName = lastName.matches("^[A-Z][a-z]*$");

        boolean areNamesDifferent = !firstName.equals(lastName);

        return isValidFirstName && isValidLastName && areNamesDifferent;
    }
}
