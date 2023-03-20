package com.wavemaker.daoImpl;

import com.wavemaker.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class BooksDaoImpl implements com.wavemaker.dao.BooksDao {
    @Autowired
    private SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(BooksDaoImpl.class);

    @Transactional
    @Override
    public String insert(Book book) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for adding a new book into library");
        try {
            session.save(book);
            logger.info("The new Book is added into library i.e., in BOOKS table");
            return "Book record added successfully";
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return "Book didn't added successfully";
        }
    }

    @Transactional
    @Override
    public String delete(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for deleting the book from library database");
        Book deleteBook = session.get(Book.class, bookId);
        try {
            session.delete(deleteBook);
            logger.info("Book of id{} is deleted from library database", bookId);
            return "Book record deleted successfully";
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return "book not deleted";
        }
    }
    @Transactional
    @Override
    public String update(Book updateBook) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for updating details in a book into library");
        try {
            session.saveOrUpdate(updateBook);
            logger.info("Book updated successfully");
            return "Book updated successfully";
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return "book not updated successfully";
        }
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting all the book records from database");
        List<Book> books = session.createQuery("FROM Book ", Book.class).getResultList();
        logger.info("Database session is closed for getting all book records from database");
        return books;
    }

    @Transactional
    @Override
    public Book getBookById(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting a book by given id");
        Book book = session.get(Book.class, bookId);
        logger.info("Database session is closed for getting a book by given id");
        return book;
    }
}