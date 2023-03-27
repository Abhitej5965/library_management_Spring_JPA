package com.wavemaker.service;

import com.wavemaker.dao_jpa.BookDao;
import com.wavemaker.dao_jpa.UserDao;
import com.wavemaker.model.Book;
import com.wavemaker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public User getUserById(int userId) {
        return userDao.getById(userId);
    }

    @Transactional
    public User userRegister(User user) {
        return userDao.saveAndFlush(user);
    }

    @Transactional
    public String addBooksToUsers(int userId, int bookId) {
        try {
            User user = getUserById(userId);
            Book book = bookDao.getById(bookId);
            Set<Book> setOfBooks = user.getBooks();
            setOfBooks.add(book);
            user.setBooks(setOfBooks);
            userDao.save(user);
            return "Record added successfully";
        } catch (Exception e) {
            return "Something went wrong,record not added";
        }
    }
}


