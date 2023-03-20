package com.wavemaker.dao;

import com.wavemaker.model.Book;

import java.util.List;

public interface BooksDao {
    String insert(Book book);

    String delete(int bookId);

    String update(Book updateBook);

    List<Book> getAllBooks();

    Book getBookById(int bookId);
}
