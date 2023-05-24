package com.gitintern.internships.services;

import com.gitintern.internships.models.Intern;
import com.gitintern.internships.models.Resume;

import java.util.Optional;

// this interface used to define the methods that will be used in the ResumeServiceImpl class
public interface ResumeService {
    void saveResume(Long userId, Resume resume);

    Optional<Resume> getResume(Long userId);

    void createResume(Intern intern);
}
