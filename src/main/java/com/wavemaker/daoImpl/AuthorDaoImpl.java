package com.wavemaker.daoImpl;

import com.wavemaker.dao.jpa.AuthorDao;
import com.wavemaker.dao.jpa.BookDao;
import com.wavemaker.model.AuthorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BookDao bookDao;

    private final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);

    @Transactional
    @Override
    public AuthorDetails saveAndFlush(AuthorDetails authorDetails) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for adding a new author details into library database");
        try {
            session.save(authorDetails);
            logger.info("The new author is added into library database i.e., in AUTHORDETAILS table");
            return authorDetails;
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteById(int authorId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for deleting a author from library database");
        AuthorDetails authorDetails = getById(authorId);
        try {
            session.delete(authorDetails);
            logger.info("Author of id{} is deleted from library database", authorId);
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
        }
    }

    @Transactional
    @Override
    public AuthorDetails save(AuthorDetails authorDetails) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is started for updating details in a author into library database");
        try {
            session.update(authorDetails);
            logger.info("Author updated successfully");
            return authorDetails;
        } catch (Exception e) {
            logger.error("something went wrong reason:{}. so the changes on the transaction are rolled back", e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public List<AuthorDetails> findAll() {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting all the author records from database");
        List<AuthorDetails> authorDetailsList = session.createQuery("from AuthorDetails", AuthorDetails.class).list();
        logger.info("Database session is closed for getting all author records from database");
        return authorDetailsList;
    }

    @Transactional
    @Override
    public AuthorDetails getById(int authorId) {
        Session session = sessionFactory.getCurrentSession();
        logger.info("Database session is opened for getting a author details  by given id");
        AuthorDetails authorDetails = session.get(AuthorDetails.class, authorId);
        logger.info("Database session is closed for getting a author by given id");
        return authorDetails;
    }


}
