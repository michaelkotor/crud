package com.kotor.crud.contollers;

import com.kotor.crud.exceptions.BadRequest;
import com.kotor.crud.models.User;
import com.kotor.crud.services.UserService;
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
import java.util.Optional;

/**
 * Controller responsible for operations with User
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return all  existing users
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * @param userId
     * @return current user
     */
    @GetMapping("users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    /**
     * @param user
     * creates new user
     * @return new user
     */
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody Optional<User> user) throws BadRequest {
        if(user.isPresent()) {
            User tempUser = userService.createNew(user.get());
            if(tempUser != null) {
                return tempUser;
            }
        }
        throw new BadRequest();
    }

    /**
     * @param userId
     * @param user
     * updates new user. if user doen't exist, creates a new one
     * @return update/new user
     */
    @PutMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long userId, @RequestBody Optional<User> user) throws BadRequest {
        if (user.isPresent()) {
            return userService.updateById(userId, user.get());
        }
        throw new BadRequest();
    }

    /**
     * @param userId
     * finds and deletes user
     * @return deleted user
     */
    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User deleteUserById(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }
}
