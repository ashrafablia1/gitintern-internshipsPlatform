package com.gitintern.internships.services.servicesInterfaces;

import com.gitintern.internships.dto.InternProfileDto;
import com.gitintern.internships.models.InternProfile;
import com.gitintern.internships.models.InternshipApplication;
import com.gitintern.internships.models.Resume;

import java.util.List;

public interface InternshipApplicationService {

    void createInternshipApplication(Long internshipId);

    List<InternshipApplication> getAllInternshipApplicationByInternshipId(Long internshipId);

    Integer internshipApplicationsCount(Long internshipId);

    InternshipApplication getInternshipApplication(Long internshipApplicationId);

    void changeStatus(Long internshipApplicationId, String status);

    InternshipApplication getInternInternshipApplicationIdByInternshipId(Long internshipId);

    List<InternshipApplication> getAllInternshipApplicationByInternProfile(InternProfile internProfile);



}
