package com.wavemaker.daoImpl;

import com.wavemaker.dao.BooksDao;
import com.wavemaker.model.Book;
import com.wavemaker.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements com.wavemaker.dao.UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private BooksDao booksDaoImpl;
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Transactional(value="transactionManager")
    @Override
    public String userRegister(User user) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for registering user");
        logger.info("Transaction in an opened session for registering user is intialized");
        try {
            logger.info("Transaction is started for adding the user into the database");
            session.save(user);
            logger.info("User record of newly registered user is added into the USER table");
            logger.info("Transaction is ended for adding the user into the database");
            return "User registered successfully";
        } catch (Exception e) {
            logger.info("Something went wrong so the last changes in this transaction are rolled back");
            return "Something went wrong user not registered cause:- " + e.getMessage();
        }
    }
    @Transactional
    @Override
    public User validateUser(String username) {
        Session session = sessionFactory.openSession();
        logger.info("Database session is opened for validating the logging user by checking is there any record with the username,which the user has provided");
        User user = null;
        try {
            user = (User) session.createQuery("FROM User WHERE username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
            logger.info("The user record with username {} provided by current logging user is there in database", username);
        } catch (Exception e) {
            logger.error("The user record with username {} provided by current logging user is not there in database,so this user has to register first", username);

        }
        session.close();
        logger.info("Database session for validating user is closed");
        return user;
    }
    @Transactional
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database Session for getting all users data started");
        List<User> users = session.createQuery("FROM User ", User.class).getResultList();
        logger.info("Database session for getting all users data closed");
        return users;
    }

    @Transactional
    @Override
    public User getUserById(int userId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database Session for getting  user data by id started");
        User user = session.get(User.class, userId);
        logger.info("Got user record where USERID={} from table USER", userId);
        logger.info("Database session for getting a user data by id is closed");
        return user;
    }

    @Transactional
    @Override
    public String addBooksToUsers(int bookId, int userId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for user to add books into his cart");
        logger.info("Database transaction for opened session is intialized");
        try {
            User user = getUserById(userId);
            Book book = booksDaoImpl.getBookById(bookId);
            Hibernate.initialize(user.getBooks());
            Set<Book> setOfBooks = user.getBooks();
            setOfBooks.add(book);
            user.setBooks(setOfBooks);
            System.out.println(user);
            logger.info("transaction in an opened session is started");
            session.update(user);
            logger.info("user record with added books into his cart are updated");
            logger.info("Transaction is commited");
            return "Record added successfully";
        } catch (Exception e) {
            logger.error("Something went wrong so the last transaction is rolled back");
            return "Record not added";
        }
    }
}
