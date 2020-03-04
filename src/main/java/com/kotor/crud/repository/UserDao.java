package com.kotor.crud.repository;

import com.kotor.crud.models.User;

import java.util.List;

public interface UserDao {
    User createNew(User user);

    User updateById(Long userId, User user);

    User deleteById(Long id);

    User findById(Long id);

    List<User> findAll();

    int test();

    User insertBase(User user);
}
