package com.postgres.Service;

import com.postgres.Repo.PersonRepository;
import com.postgres.model.Person;
import com.postgres.util.FileLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        FileLogger.logPersonOperation("Added person: " + savedPerson);
        return savedPerson;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
        FileLogger.logPersonOperation("Deleted person with ID: " + id);
    }

    public Person updatePersonById(Long id, Person updatedPerson) {
        Optional<Person> existingPersonOptional = personRepository.findById(id);
        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            existingPerson.setFirstName(updatedPerson.getFirstName());
            existingPerson.setLastName(updatedPerson.getLastName());

            Person savedPerson = personRepository.save(existingPerson);
            FileLogger.logPersonOperation("Updated person: " + savedPerson);
            return savedPerson;
        } else {
            FileLogger.logPersonOperation("Person not found with ID: " + id);
            return null;
        }
    }

    public List<Person> findPersonByLastName(String lastName){
        return personRepository.findPersonsByLastName(lastName);
    }
}
