package com.wavemaker.controller;

import com.wavemaker.model.AuthorDetails;
import com.wavemaker.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorDetailsController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDetails> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public AuthorDetails getAuthorById(@PathVariable(value = "authorId") int authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping
    public void addAuthorDetails(@RequestBody AuthorDetails authorDetails) {
        authorService.createAuthor(authorDetails);
    }

    @PatchMapping
    public void updateAuthor(@RequestBody AuthorDetails authorDetails) {
        authorService.updateAuthorDetails(authorDetails);
    }

    @DeleteMapping("/{authorId}")
    public String deleteAuthor(@PathVariable(value = "authorId") int authorId) {
        authorService.deleteByAuthorId(authorId);
        return "book deleted successfully";
    }
}
