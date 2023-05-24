package com.gitintern.internships.repositories;


import com.gitintern.internships.models.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// this interface is used to access the database
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
