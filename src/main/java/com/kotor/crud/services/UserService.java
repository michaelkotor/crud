package com.kotor.crud.services;

import com.kotor.crud.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long userId);

    User createNew(User user);

    User deleteById(Long userId);

    User updateById(Long userId, User user);
}
