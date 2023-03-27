package com.wavemaker.service;

import com.wavemaker.dao_jpa.BookDao;
import com.wavemaker.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    @Transactional
    public Book createBooks(Book book) {
        return bookDao.saveAndFlush(book);
    }

    @Transactional
    public void deleteBookBYId(int bookId) {
        bookDao.deleteById(bookId);
    }

    @Transactional
    public Book updateBooks(Book book) {
        Book updatedBook = getBookById(book.getBookId());
        updatedBook.setBookName(book.getBookName());
        updatedBook.setBookGenre(book.getBookGenre());
        updatedBook.setBookDesc(book.getBookDesc());
        updatedBook.setPrice(book.getPrice());
        updatedBook.setNoOfCopies(book.getNoOfCopies());
        return bookDao.save(book);
    }

    @Transactional
    public List<Book> getALlBooks() {
        return bookDao.findAll();
    }

    @Transactional
    public Book getBookById(int bookId) {
        return bookDao.getById(bookId);
    }

}
