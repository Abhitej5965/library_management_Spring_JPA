package com.wavemaker.controller;

import com.wavemaker.model.Book;
import com.wavemaker.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping
    public List<Book> getAllBooks() {
        return booksService.getALlBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId) {
        return booksService.getBookById(bookId);
    }

    @PostMapping
    public String addBooks(@RequestBody Book book) {
        return booksService.addBooks(book);
    }

    @PutMapping
    public String updateBook(@RequestBody Book book) {
        return booksService.updateBooks(book);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBooks(@PathVariable("bookId") int bookId) {
        return booksService.deleteBooks(bookId);
    }
}
