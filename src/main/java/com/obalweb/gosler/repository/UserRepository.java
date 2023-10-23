package com.obalweb.gosler.repository;

import com.obalweb.gosler.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    public User findOne(Integer id);

    public List<User> findAll();

    public void save(User user);

    public long saveAndReturnId(User user);

    public void update(User user);

    public Boolean delete(Integer id);
}
