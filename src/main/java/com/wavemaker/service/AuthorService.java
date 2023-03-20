package com.wavemaker.service;

import com.wavemaker.dao.AuthorDao;
import com.wavemaker.model.AuthorDetails;
import com.wavemaker.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorDao authorDao;

    public void addAuthorDetails(AuthorDetails authorDetails) {
        authorDao.addAuthor(authorDetails);
    }

    public void deleteAuthorDetails(int authorId) {
        authorDao.deleteAuthorById(authorId);
    }

    public void updateAuthorDetails(AuthorDetails authorDetails) {
        authorDao.updateAuthor(authorDetails);
    }

    public List<AuthorDetails> getAllAuthorsDetails() {
        return authorDao.getAllAuthors();
    }

    public AuthorDetails getAuthorDetailsById(int authorId) {
        return authorDao.getAuthorById(authorId);
    }

    public List<Book> getBooksByAuthorId(int authorId) {
        return authorDao.getBooksByAuthorId(authorId);
    }
}
