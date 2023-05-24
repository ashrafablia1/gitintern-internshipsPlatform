package com.gitintern.internships.repositories;

import com.gitintern.internships.models.Company;
import com.gitintern.internships.models.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// this interface is used to access the database
@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {
    CompanyProfile findProfileByCompany(Company company);
}

