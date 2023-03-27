package com.wavemaker.dao_jpa;

import com.wavemaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    @Transactional
    @Query(value = "SELECT * FROM USER where USERNAME=:username",nativeQuery = true)
    public User validateUser(@Param("username") String username);
}
