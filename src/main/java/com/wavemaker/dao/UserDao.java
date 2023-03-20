package com.wavemaker.dao;

import com.wavemaker.model.User;

import java.util.List;

public interface UserDao {
    String userRegister(User user);

    User validateUser(String username);

    List<User> getAllUsers();

    User getUserById(int userId);

    String addBooksToUsers(int bookId, int userId);
}
