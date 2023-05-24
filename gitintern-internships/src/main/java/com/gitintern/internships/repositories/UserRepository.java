package com.gitintern.internships.repositories;


import com.gitintern.internships.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// this interface is used to access the database
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}