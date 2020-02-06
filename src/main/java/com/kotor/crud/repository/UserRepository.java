package com.kotor.crud.repository;

import com.kotor.crud.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class UserRepository {
    private List<User> users = new ArrayList();


    public User findById(Long userId) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(userId)) return users.get(i);
        }
        return null;
    }


    public List<User> findAll() {
        return users;
    }

    public User createNew(User user) {
        User tempUser = new User();
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setId(Math.abs(new Random().nextLong()));
        users.add(tempUser);
        return tempUser;
    }

    public User deleteById(Long userId) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(userId)) {
                return users.remove(i);
            }
        }
        return null;
    }

    public User updateById(Long userId, User user) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(userId)) {
                Long tempId = users.get(i).getId();
                user.setId(tempId);
                users.set(i, user);
                return user;
            }
        }
        return createNew(user);
    }
}
