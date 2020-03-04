package com.kotor.crud.repository.impl;

import com.kotor.crud.models.User;
import com.kotor.crud.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UserRepository implements UserDao {
    private List<User> users = new ArrayList();


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
//
    public int test() {
        return jdbcTemplate.getCacheLimit();
    }

    @Override
    public User insertBase(User user) {
        String sql = "insert into users (username, password) VALUES (:username, :password)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("password", user.getPassword());

        jdbcTemplate.update(sql, params);
        return user;
    }

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
