package com.wavemaker.dao;

import com.wavemaker.model.User;

import java.util.List;

public interface UserDao {
    User saveAndFlush(User user);

    User validateUser(String username);

    List<User> findAll();

    User getById(int userId);

    void save(User user);
}
