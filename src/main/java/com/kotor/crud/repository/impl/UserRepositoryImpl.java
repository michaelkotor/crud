package com.kotor.crud.repository.impl;

import com.kotor.crud.models.User;
import com.kotor.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public User findById(Long userId) {
        String sql = "SELECT * FROM users WHERE users.id = :userId";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("userId", userId);
        return jdbcTemplate.queryForObject(sql, paramSource, new UserRowMapper());
    }


    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> result = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new MapSqlParameterSource());
        for(Map row : rows) {
            User tempUser = new User();
            tempUser.setId( (Long.valueOf((int)row.get("id"))));
            tempUser.setUsername((String) row.get("username"));
            tempUser.setPassword((String) row.get("password"));
            result.add(tempUser);
        }
        return result;
    }

    public User createNew(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (:username, :password)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("username", user.getUsername());
        paramSource.addValue("password", user.getPassword());
        jdbcTemplate.update(sql, paramSource);
        user.setId(findByUsernameAndPassword(user.getUsername(), user.getPassword()));
        return user;
    }

    public User deleteById(Long userId) {
        String sql = "DELETE FROM users WHERE id=:userId";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("userId", userId);
        User deleted = findById(userId);
        jdbcTemplate.update(sql, paramSource);
        return deleted;
    }

    public User updateById(Long userId, User user) {
        String sql = "INSERT INTO users (username, password) VALUES (:username, :password)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("username", user.getUsername());
        paramSource.addValue("password", user.getPassword());
        jdbcTemplate.update(sql, paramSource);
        user.setId(userId);
        return user;
    }

    public Long findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT id FROM users WHERE users.username=:username AND users.password=:password LIMIT 1";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("username", username);
        paramSource.addValue("password", password);
        return jdbcTemplate.queryForObject(sql, paramSource, Long.class);
    }


    private static final class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
