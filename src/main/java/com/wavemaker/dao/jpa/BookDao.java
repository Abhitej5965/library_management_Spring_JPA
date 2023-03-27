package com.wavemaker.dao.jpa;

import com.wavemaker.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM BOOKS where authorDetails_AUTHOR_ID=:id", nativeQuery = true)
    public List<Book> getBooksByAuthorId(@Param("id") int id);
}
