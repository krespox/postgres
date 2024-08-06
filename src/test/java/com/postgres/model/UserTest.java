package com.postgres.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();

        user.setId(1L);
        assertEquals(1L, user.getId());

        user.setFirstName("John");
        assertEquals("John", user.getFirstName());

        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }
}
