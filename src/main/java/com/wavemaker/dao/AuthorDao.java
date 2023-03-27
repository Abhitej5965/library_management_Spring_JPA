package com.wavemaker.dao;

import com.wavemaker.model.AuthorDetails;

import java.util.List;

public interface AuthorDao {
    AuthorDetails saveAndFlush(AuthorDetails authorDetails);

    void deleteById(int authorId);

    //Update
    AuthorDetails save(AuthorDetails authorDetails);

    List<AuthorDetails> findAll();

    AuthorDetails getById(int authorId);

}
