package com.wavemaker.daoImpl;

import com.wavemaker.dao.jpa.BookDao;
import com.wavemaker.model.AuthorDetails;
import com.wavemaker.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    @Transactional
    @Override
    public Book saveAndFlush(Book book) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for adding a new book into library");
        try {
            session.save(book);
            logger.info("The new Book is added into library i.e., in BOOKS table");
            return book;
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteById(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for deleting the book from library database");
        Book deleteBook = session.get(Book.class, bookId);
        try {
            session.delete(deleteBook);
            logger.info("Book of id{} is deleted from library database", bookId);
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
        }
    }

    @Transactional
    @Override
    public Book save(Book updateBook) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for updating details in a book into library");
        try {
            session.saveOrUpdate(updateBook);
            logger.info("Book updated successfully");
            return updateBook;
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting all the book records from database");
        List<Book> books = session.createQuery("FROM Book ", Book.class).getResultList();
        logger.info("Database session is closed for getting all book records from database");
        return books;
    }

    @Transactional
    @Override
    public Book getById(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting a book by given id");
        Book book = session.get(Book.class, bookId);
        logger.info("Database session is closed for getting a book by given id");
        return book;
    }

    @Transactional
    @Override
    public List<Book> getBooksByAuthorId(int authorDetails) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> booksRoot = criteriaQuery.from(Book.class);
        Join<Book, AuthorDetails> authorDetailsJoin = booksRoot.join("authorDetails");
        criteriaQuery.where(criteriaBuilder.equal(booksRoot.get("authorDetails"), authorDetails));
        List<Book> bookList = session.createQuery(criteriaQuery).getResultList();
        return bookList;
    }
}