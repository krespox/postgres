package com.postgres.service;

import com.postgres.model.User;
import com.postgres.repozytory.UserRepository;
import com.postgres.util.FileLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserMenagment {
    @Autowired
    private UserRepository userRepository;

    public User add(User user) {
        User savedUser = userRepository.save(user);
        FileLogger.logOperation("Added user: " + savedUser);
        return savedUser;
    }

    public List<User> getALL() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);

    }

    public void delete(Long id) {
        userRepository.deleteById(id);
        FileLogger.logOperation("Deleted user with ID: " + id);
    }

    public User updateByID(Long id, User updatedUser) {
        Optional<User> existingPersonOptional = userRepository.findById(id);
        if (existingPersonOptional.isPresent()) {
            User existingUser = existingPersonOptional.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());

            User savedUser = userRepository.save(existingUser);
            FileLogger.logOperation("Updated : " + savedUser);
            return savedUser;
        } else {
            FileLogger.logOperation("Not found with ID: " + id);
            return null;
        }
    }

    public List<User> findBy(String lastName){
        return userRepository.findBy(lastName);
    }
}
