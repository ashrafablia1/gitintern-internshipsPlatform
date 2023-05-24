package com.gitintern.internships.repositories;

import com.gitintern.internships.models.Intern;
import com.gitintern.internships.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// this interface is used to access the database
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findResumeByIntern(Intern intern);

}
