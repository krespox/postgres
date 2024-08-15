package com.postgres.controller;

import com.postgres.model.User;
import com.postgres.service.UserMenagment;
import com.postgres.util.FileLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserMenagment userMenagment;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
    }

 /*   @Test
    public void testAdd() {
        when(userMenagment.add(any(User.class))).thenReturn(user);
        ResponseEntity<String> response = userController.add(user);

        FileLogger.logTestResult("testAdd: " + (response.getStatusCode() == HttpStatus.CREATED));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("user created", response.getBody());
    }*/

    @Test
    public void testGetAll() {
        List<User> users = Arrays.asList(user);
        when(userMenagment.getALL()).thenReturn(users);

        List<User> response = userController.getAll();
        FileLogger.logTestResult("testGetAll: " + (response.size() == 1 && response.get(0).equals(user)));
        assertEquals(1, response.size());
        assertEquals(user, response.get(0));
    }

    @Test
    public void testGetBy() {
        when(userMenagment.getById(1L)).thenReturn(user);
        ResponseEntity<User> response = userController.getBy(1L);

        FileLogger.logTestResult("testGetById: " + (response.getStatusCode() == HttpStatus.OK && response.getBody().equals(user)));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testDelete() {
        doNothing().when(userMenagment).delete(1L);
        ResponseEntity<String> response = userController.delete(1L);

        FileLogger.logTestResult("testDelete: " + (response.getStatusCode() == HttpStatus.NO_CONTENT));
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("User deleted", response.getBody());
    }

/*    @Test
    public void testUpdate() {
        when(userMenagment.updateByID(eq(1L), any(User.class))).thenReturn(user);
        ResponseEntity<String> response = userController.update(1L, user);

        FileLogger.logTestResult("testUpdate: " + (response.getStatusCode() == HttpStatus.OK));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User update", response.getBody());
    }*/
}
