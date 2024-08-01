package com.postgres.Service;

import com.postgres.Repo.PersonRepository;
import com.postgres.model.Person;
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
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
    }

    @Test
    public void testAddPerson() {
        when(personRepository.save(any(Person.class))).thenReturn(person);
        Person result = personService.addPerson(person);

        FileLogger.logTestResult("testAddPerson: " + (result.equals(person)));
        assertEquals(person, result);
    }

    @Test
    public void testGetAllPersons() {
        List<Person> persons = Arrays.asList(person);
        when(personRepository.findAll()).thenReturn(persons);

        List<Person> result = personService.getAllPersons();
        FileLogger.logTestResult("testGetAllPersons: " + (result.size() == 1 && result.get(0).equals(person)));
        assertEquals(1, result.size());
        assertEquals(person, result.get(0));
    }

    @Test
    public void testGetPersonById() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        Person result = personService.getPersonById(1L);

        FileLogger.logTestResult("testGetPersonById: " + (result.equals(person)));
        assertEquals(person, result);
    }

    @Test
    public void testGetPersonByIdNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());
        Person result = personService.getPersonById(1L);

        FileLogger.logTestResult("testGetPersonByIdNotFound: " + (result == null));
        assertNull(result);
    }

    @Test
    public void testDeletePerson() {
        doNothing().when(personRepository).deleteById(1L);
        personService.deletePerson(1L);

        FileLogger.logTestResult("testDeletePerson: executed");
        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdatePersonById() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person updatedPerson = new Person();
        updatedPerson.setFirstName("Jane");
        updatedPerson.setLastName("Doe");

        Person result = personService.updatePersonById(1L, updatedPerson);

        FileLogger.logTestResult("testUpdatePersonById: " + ("Jane".equals(result.getFirstName()) && "Doe".equals(result.getLastName())));
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    public void testUpdatePersonByIdNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        Person updatedPerson = new Person();
        updatedPerson.setFirstName("Jane");
        updatedPerson.setLastName("Doe");

        Person result = personService.updatePersonById(1L, updatedPerson);

        FileLogger.logTestResult("testUpdatePersonByIdNotFound: " + (result == null));
        assertNull(result);
    }
}
