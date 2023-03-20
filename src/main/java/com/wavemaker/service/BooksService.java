package com.wavemaker.service;

import com.wavemaker.dao.BooksDao;
import com.wavemaker.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksDao booksDao;

    public String addBooks(Book book) {
        System.out.println(book);
        return booksDao.insert(book);
    }

    public String deleteBooks(int bookId) {
        return booksDao.delete(bookId);
    }

    public String updateBooks(Book book) {
        return booksDao.update(book);
    }

    public List<Book> getALlBooks() {
        return booksDao.getAllBooks();
    }

    public Book getBookById(int bookId) {
        return booksDao.getBookById(bookId);
    }

}
