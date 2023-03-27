package com.wavemaker.dao;

import com.wavemaker.model.Book;

import java.util.List;

public interface BookDao {
    Book saveAndFlush(Book book);

    void deleteById(int bookId);

    Book save(Book updateBook);

    List<Book> findAll();

    Book getById(int bookId);

    List<Book> getBooksByAuthorId(int authorDetails);
}
