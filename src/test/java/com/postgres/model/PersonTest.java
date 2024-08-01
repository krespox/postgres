package com.postgres.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    public void testGettersAndSetters() {
        Person person = new Person();

        person.setId(1L);
        assertEquals(1L, person.getId());

        person.setFirstName("John");
        assertEquals("John", person.getFirstName());

        person.setLastName("Doe");
        assertEquals("Doe", person.getLastName());
    }
}
