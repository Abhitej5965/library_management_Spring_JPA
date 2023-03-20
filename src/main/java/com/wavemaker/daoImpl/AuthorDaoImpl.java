package com.wavemaker.daoImpl;

import com.wavemaker.dao.AuthorDao;
import com.wavemaker.model.AuthorDetails;
import com.wavemaker.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
public class AuthorDaoImpl implements AuthorDao {
    @Autowired
    private SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);

    @Transactional
    @Override
    public String addAuthor(AuthorDetails authorDetails) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for adding a new author details into library database");
        try {
            session.save(authorDetails);
            logger.info("The new author is added into library database i.e., in AUTHORDETAILS table");
            return "Author record added successfully";
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return "Author record not added";
        }
    }

    @Transactional
    @Override
    public String deleteAuthorById(int authorId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for deleting a author from library database");
        AuthorDetails authorDetails = session.get(AuthorDetails.class, authorId);
        List<Book> bookList = getBooksByAuthorId(authorId);
        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                book.setAuthorDetails(null);
            }
        }
        try {
            session.delete(authorDetails);
            logger.info("Author of id{} is deleted from library database", authorId);
            return "Author deleted successfully";
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return "Author nor deleted";
        }
    }
    @Transactional
    @Override
    public String updateAuthor(AuthorDetails authorDetails) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for updating details in a author into library database");
        try {
            session.update(authorDetails);
            logger.info("Author updated successfully");
            return "Author details updated successfully";
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return "Author details not updated";
        }
    }
    @Transactional
    @Override
    public List<AuthorDetails> getAllAuthors() {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting all the author records from database");
        List<AuthorDetails> authorDetailsList = session.createQuery("from AuthorDetails", AuthorDetails.class).list();
        logger.info("Database session is closed for getting all author records from database");
        return authorDetailsList;
    }

    @Transactional
    @Override
    public AuthorDetails getAuthorById(int authorId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting a author details  by given id");
        AuthorDetails authorDetails = session.get(AuthorDetails.class, authorId);
        logger.info("Database session is closed for getting a author by given id");
        return authorDetails;
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
