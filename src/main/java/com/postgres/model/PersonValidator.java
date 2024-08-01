package com.postgres.model;

public class PersonValidator {

    public static boolean isValid(Person person) {
        if (person == null) return false;

        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        boolean isValidFirstName = firstName.matches("^[A-Z][a-z]*$");
        boolean isValidLastName = lastName.matches("^[A-Z][a-z]*$");

        boolean areNamesDifferent = !firstName.equals(lastName);

        return isValidFirstName && isValidLastName && areNamesDifferent;
    }
}
