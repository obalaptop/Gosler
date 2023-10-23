package com.obalweb.gosler.repository;

import com.obalweb.gosler.exception.InternalServerError;
import com.obalweb.gosler.exception.UserNotFound;
import com.obalweb.gosler.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findOne(Integer id) {
        var sqlQuery = UserQuery.FIND_ONE;
        try {
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToUser, id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFound("Invalid user id");
        } catch (Exception e) {
            throw new InternalServerError("Eternal Server Error");
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User user) {
        var sqlQuery = UserQuery.SAVE;

        jdbcTemplate.update(sqlQuery, user.name(),
                user.surname(),
                user.username(),
                user.email());
    }

    @Override
    public long saveAndReturnId(User user) {
        var sqlQuery = UserQuery.SAVE_AND_RETURN_ID;

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[] {"id"});
            stmt.setString(1, user.name());
            stmt.setString(2, user.surname());
            stmt.setString(3, user.username());
            stmt.setString(4, user.email());

            return stmt;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    private User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        var user = new User(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("username"),
                resultSet.getString("email"));
        return user;
    }
}
