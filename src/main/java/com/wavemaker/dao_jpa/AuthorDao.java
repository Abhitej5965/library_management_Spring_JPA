package com.wavemaker.dao_jpa;

import com.wavemaker.model.AuthorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<AuthorDetails,Integer> {
}
