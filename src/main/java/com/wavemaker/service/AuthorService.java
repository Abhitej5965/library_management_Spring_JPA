package com.wavemaker.service;

import com.wavemaker.dao.jpa.AuthorDao;
import com.wavemaker.dao.jpa.BookDao;
import com.wavemaker.model.AuthorDetails;
import com.wavemaker.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Transactional
    public AuthorDetails createAuthor(AuthorDetails authorDetails) {
        return authorDao.saveAndFlush(authorDetails);
    }

    @Transactional
    public void deleteByAuthorId(int authorId) {
        List<Book> bookList = bookDao.getBooksByAuthorId(authorId);
        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                book.setAuthorDetails(null);
            }
        }
        authorDao.deleteById(authorId);
    }

    @Transactional
    public AuthorDetails updateAuthorDetails(AuthorDetails authorDetails) {
        AuthorDetails newAuthorDetails = getAuthorById(authorDetails.getAuthorId());
        newAuthorDetails.setAuthorName(authorDetails.getAuthorName());
        newAuthorDetails.setAuthorEmail(authorDetails.getAuthorEmail());
        newAuthorDetails.setAuthorPhoneNo(authorDetails.getAuthorPhoneNo());
        newAuthorDetails.setAuthorAddress(authorDetails.getAuthorAddress());
        return authorDao.save(authorDetails);
    }

    @Transactional
    public List<AuthorDetails> getAllAuthors() {
        return authorDao.findAll();
    }

    @Transactional
    public AuthorDetails getAuthorById(int authorId) {
        return authorDao.getById(authorId);
    }
}
