package com.gitintern.internships.services.servicesInterfaces;

import com.gitintern.internships.models.Intern;
import com.gitintern.internships.models.Resume;

// this interface used to define the methods that will be used in the ResumeServiceImpl class
public interface ResumeService {
    void saveResume(Long userId, Resume resume);

    Resume getResumeByInternId(Long userId);

    Resume getResumeByResumeId(Long resumeId);

    Long getResumeId(Long userId);

    void createResume(Intern intern);
}
