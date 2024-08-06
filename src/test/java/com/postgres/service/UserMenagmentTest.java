package com.postgres.service;

import com.postgres.model.User;
import com.postgres.repozytory.UserRepository;
import com.postgres.util.FileLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserMenagmentTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserMenagment userMenagment;


    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Test
    public void testAdd() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userMenagment.add(user);

        FileLogger.logTestResult("testAdduser: " + (result.equals(user)));
        assertEquals(user, result);
    }

    @Test
    public void testGetALL() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userMenagment.getALL();
        FileLogger.logTestResult("testGetAlluser " + (result.size() == 1 && result.get(0).equals(user)));
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    public void testGetById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = userMenagment.getById(1L);

        FileLogger.logTestResult("testGetPersonById: " + (result.equals(user)));
        assertEquals(user, result);
    }

    @Test
    public void testGetByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        User result = userMenagment.getById(1L);

        FileLogger.logTestResult("testGetPersonByIdNotFound: " + (result == null));
        assertNull(result);
    }

    @Test
    public void testDelete() {
        doNothing().when(userRepository).deleteById(1L);
        userMenagment.delete(1L);

        FileLogger.logTestResult("testDeletePerson: executed");
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateByID() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = new User();
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Doe");

        User result = userMenagment.updateByID(1L, updatedUser);

        FileLogger.logTestResult("testUpdatePersonById: " + ("Jane".equals(result.getFirstName()) && "Doe".equals(result.getLastName())));
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    public void testUpdateByIDNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User updatedUser = new User();
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Doe");

        User result = userMenagment.updateByID(1L, updatedUser);

        FileLogger.logTestResult("testUpdatePersonByIdNotFound: " + (result == null));
        assertNull(result);
    }
}
