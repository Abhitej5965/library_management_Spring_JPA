package com.wavemaker.dao;

import com.wavemaker.model.AuthorDetails;
import com.wavemaker.model.Book;

import java.util.List;

public interface AuthorDao {
    String addAuthor(AuthorDetails authorDetails);

    String deleteAuthorById(int authorId);

    String updateAuthor(AuthorDetails authorDetails);

    List<AuthorDetails> getAllAuthors();

    AuthorDetails getAuthorById(int authorId);

    List<Book> getBooksByAuthorId(int authorDetails);
}
