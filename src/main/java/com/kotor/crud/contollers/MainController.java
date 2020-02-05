package com.kotor.crud.contollers;

import com.kotor.crud.models.User;
import com.kotor.crud.repository.MainRepository;
import com.kotor.crud.utilityes.RestCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class MainController {

    @Autowired
    private MainRepository repository;

    public MainController() {

    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return repository.findAllUsers();
    }

    @GetMapping("getUser/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return repository.findUserById(userId);
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        RestCheck.checkFound(user);
        return repository.createNewUser(user);
    }

    @PutMapping("/updateUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        RestCheck.checkFound(user);
        return null;
    }

    @DeleteMapping("/deleteUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User deleteUserById(@PathVariable Long userId) {
        return null;
    }

}
