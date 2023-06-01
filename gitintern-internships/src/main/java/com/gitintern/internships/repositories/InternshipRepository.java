package com.gitintern.internships.repositories;

import com.gitintern.internships.models.Company;
import com.gitintern.internships.models.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//checked
@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findAllInternshipsByCompany(Company company);

}
