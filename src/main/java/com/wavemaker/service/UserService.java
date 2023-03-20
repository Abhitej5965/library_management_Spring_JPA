package com.wavemaker.service;

import com.wavemaker.dao.UserDao;
import com.wavemaker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;


    public String userRegister(User user) {
        return userDao.userRegister(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public String addBooksToUsers(int bookId, int userId) {
        return userDao.addBooksToUsers(bookId, userId);
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }
}


