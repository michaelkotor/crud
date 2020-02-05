package com.kotor.crud.repository;

import com.kotor.crud.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MainRepository {
    private List<User> users = new ArrayList();

    public User findUserById(Long userId) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(userId)) return users.get(i);
        }
        return null;
    }


    public List<User> findAllUsers() {
        return users;
    }

    public User createNewUser(User user) {
        User tempUser = new User();
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setId(Math.abs(new Random().nextLong()));
        users.add(tempUser);
        return tempUser;
    }
}
