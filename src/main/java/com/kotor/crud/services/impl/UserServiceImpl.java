package com.kotor.crud.services.impl;

import com.kotor.crud.models.User;
import com.kotor.crud.repository.UserRepository;
import com.kotor.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User createNew(User user) {
        try {
            userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            return userRepository.createNew(user);
        }
        return null;
    }

    @Override
    public User deleteById(Long userId) {
        return userRepository.deleteById(userId);
    }

    @Override
    public User updateById(Long userId, User user) {
        return userRepository.updateById(userId, user);
    }
}
