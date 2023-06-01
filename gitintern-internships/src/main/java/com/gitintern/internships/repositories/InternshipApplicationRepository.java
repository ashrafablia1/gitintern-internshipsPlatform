package com.gitintern.internships.repositories;

import com.fasterxml.jackson.databind.deser.UnresolvedId;
import com.gitintern.internships.models.Company;
import com.gitintern.internships.models.InternProfile;
import com.gitintern.internships.models.Internship;
import com.gitintern.internships.models.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {
    List<InternshipApplication> findAllByInternship(Internship internship);

    InternshipApplication findInternshipApplicationByInternshipAndInternProfile(Internship internship, InternProfile internProfile);

    List<InternshipApplication> findAllByInternProfile(InternProfile internProfile);


}
