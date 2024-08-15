package com.postgres.controller;

import com.postgres.model.User;
import com.postgres.service.UserMenagment;
import com.postgres.model.UserValidator;
/*import com.postgres.util.NodeRedClient;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserMenagment userMenagment;
  /*  private final NodeRedClient nodeRedClient;*/


    @Value("${node-red.url}")
    private String suffix;

    @Autowired
    public UserController(UserMenagment userMenagment/*, NodeRedClient nodeRedClient*/) {
        this.userMenagment = userMenagment;
       /* this.nodeRedClient = nodeRedClient;*/
    }

    @PostMapping
    public ResponseEntity<String> add(@Valid @RequestBody User user) {
        if (UserValidator.isValid(user)) {
            userMenagment.add(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong user validators");
        }
    }

    @GetMapping
    public List<User> getAll() {
        return userMenagment.getALL();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getBy(@PathVariable Long id) {
        User user = userMenagment.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userMenagment.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        if (UserValidator.isValid(updatedUser)) {
            User user = userMenagment.updateByID(id, updatedUser);
            if (user != null) {
                return ResponseEntity.ok("Update User");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong user validators");
        }
    }

   /* new one

    @PostMapping("send-all-to-node-red")
    public ResponseEntity<String> send(){
        List<User> allUsers = userMenagment.getALL();

        StringBuilder dataToSend = new StringBuilder();
        for (User user : allUsers) {
            String formattedData = formatDataForNodeRed(user);
            dataToSend.append(formattedData).append("\n");
        }
        nodeRedClient.sendToNodeRed(dataToSend.toString());

        return ResponseEntity.ok("Data snt to Node-red");
    }*/

    @GetMapping("by-lastname/{lastName}")
    public ResponseEntity<List<User>> getByLastName(@PathVariable String lastName){
        List<User> users = userMenagment.findBy(lastName);
        if (users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
        }else
        {
            return ResponseEntity.ok(users);
        }
    }

    private  String formatDataForNodeRed(User user){
        return  "ID: " + user.getId() + "First Name: " + user.getFirstName() + "Last Name: " + user.getLastName();
    }


}
