package com.postgres.Controller;

import com.postgres.model.Person;
import com.postgres.Service.PersonService;
import com.postgres.model.PersonValidator;
import com.postgres.util.NodeRedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Validated
public class PersonController {

    private final PersonService personService;
    private final NodeRedClient nodeRedClient;

    @Value("${node-red.url}")
    private String suffix;

    @Autowired
    public PersonController(PersonService personService, NodeRedClient nodeRedClient) {
        this.personService = personService;
        this.nodeRedClient = nodeRedClient;
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@Valid @RequestBody Person person) {
        if (PersonValidator.isValid(person)) {
            personService.addPerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body("Person created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong person validators");
        }
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Person deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @Valid @RequestBody Person updatedPerson) {
        if (PersonValidator.isValid(updatedPerson)) {
            Person person = personService.updatePersonById(id, updatedPerson);
            if (person != null) {
                return ResponseEntity.ok("Update Person");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong person validators");
        }
    }

    // new one

    @PostMapping("send-all-to-node-red")
    public ResponseEntity<String> sendAllPersonsToNodeRed(){
        List<Person> allPersons = personService.getAllPersons();

        StringBuilder dataToSend = new StringBuilder();
        for (Person person : allPersons) {
            String formattedData = formatDataForNodeRed(person);
            dataToSend.append(formattedData).append("\n");
        }
        nodeRedClient.sendToNodeRed(dataToSend.toString());

        return ResponseEntity.ok("Data snt to Node-red");
    }

    @GetMapping("by-lastname/{lastName}")
    public ResponseEntity<List<Person>> getPersonByLastName(@PathVariable String lastName){
        List<Person> persons = personService.findPersonByLastName(lastName);
        if (persons.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(persons);
        }else
        {
            return ResponseEntity.ok(persons);
        }
    }

    private  String formatDataForNodeRed(Person person){
        return  "ID: " + person.getId() + "First Name: " + person.getFirstName() + "Last Name: " + person.getLastName();
    }


}
