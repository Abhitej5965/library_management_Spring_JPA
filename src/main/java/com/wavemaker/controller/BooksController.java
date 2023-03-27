package com.wavemaker.controller;

import com.wavemaker.model.Book;
import com.wavemaker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getALlBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public Book addBooks(@RequestBody Book book) {
        return bookService.createBooks(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBooks(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBooks(@PathVariable("bookId") int bookId) {
        bookService.deleteBookBYId(bookId);
    }
}
