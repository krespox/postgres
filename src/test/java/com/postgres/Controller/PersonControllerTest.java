package com.postgres.Controller;

import com.postgres.Service.PersonService;
import com.postgres.model.Person;
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
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
    }

    @Test
    public void testAddPerson() {
        when(personService.addPerson(any(Person.class))).thenReturn(person);
        ResponseEntity<String> response = personController.addPerson(person);

        FileLogger.logTestResult("testAddPerson: " + (response.getStatusCode() == HttpStatus.CREATED));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Osoba utworzona pomyślnie", response.getBody());
    }

    @Test
    public void testGetAllPersons() {
        List<Person> persons = Arrays.asList(person);
        when(personService.getAllPersons()).thenReturn(persons);

        List<Person> response = personController.getAllPersons();
        FileLogger.logTestResult("testGetAllPersons: " + (response.size() == 1 && response.get(0).equals(person)));
        assertEquals(1, response.size());
        assertEquals(person, response.get(0));
    }

    @Test
    public void testGetPersonById() {
        when(personService.getPersonById(1L)).thenReturn(person);
        ResponseEntity<Person> response = personController.getPersonById(1L);

        FileLogger.logTestResult("testGetPersonById: " + (response.getStatusCode() == HttpStatus.OK && response.getBody().equals(person)));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(person, response.getBody());
    }

    @Test
    public void testDeletePerson() {
        doNothing().when(personService).deletePerson(1L);
        ResponseEntity<String> response = personController.deletePerson(1L);

        FileLogger.logTestResult("testDeletePerson: " + (response.getStatusCode() == HttpStatus.NO_CONTENT));
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Osoba usunięta pomyślnie", response.getBody());
    }

    @Test
    public void testUpdatePerson() {
        when(personService.updatePersonById(eq(1L), any(Person.class))).thenReturn(person);
        ResponseEntity<String> response = personController.updatePerson(1L, person);

        FileLogger.logTestResult("testUpdatePerson: " + (response.getStatusCode() == HttpStatus.OK));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Osoba zaktualizowana pomyślnie", response.getBody());
    }
}
