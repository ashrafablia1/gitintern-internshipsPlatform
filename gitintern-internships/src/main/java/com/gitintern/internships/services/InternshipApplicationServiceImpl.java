package com.gitintern.internships.services;

import com.gitintern.internships.models.*;
import com.gitintern.internships.repositories.InternshipApplicationRepository;
import com.gitintern.internships.services.servicesInterfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class InternshipApplicationServiceImpl implements InternshipApplicationService {

         private final InternshipApplicationRepository internshipApplicationRepository;
         private final InternshipService internshipService;

         private final InternProfileService internProfileService;

         private final ResumeService resumeService;
         private final UserService userService;


    @Override
    public void createInternshipApplication(Long internshipId) {
        Internship internship = internshipService.getById(internshipId);
        InternProfile internProfile = internProfileService.findProfileByInternId(userService.extractId());
        Resume resume = resumeService.getResumeByInternId(userService.extractId());
        InternshipApplication internshipApplication = new InternshipApplication(internship, internProfile, resume);
        internshipApplicationRepository.save(internshipApplication);
    }

    @Override
    public List<InternshipApplication> getAllInternshipApplicationByInternshipId(Long internshipId) {
        Internship internship = internshipService.getById(internshipId);
            List<InternshipApplication> internshipApplications = internshipApplicationRepository.findAllByInternship(internship);
        Collections.reverse(internshipApplications);
        return internshipApplications;
    }

    @Override
    public Integer internshipApplicationsCount(Long internshipId) {
        return getAllInternshipApplicationByInternshipId(internshipId).size();
    }

    @Override
    public InternshipApplication getInternshipApplication(Long internshipApplicationId) {
        return internshipApplicationRepository.findById(internshipApplicationId).orElse(null);
    }

    @Override
    public void changeStatus(Long internshipApplicationId, String status) {
        InternshipApplication internshipApplication = getInternshipApplication(internshipApplicationId);
        internshipApplication.setStatus(Status.valueOf(status.toUpperCase()));
        internshipApplicationRepository.save(internshipApplication);
    }



    @Override
    public InternshipApplication getInternInternshipApplicationIdByInternshipId(Long internshipId) {
        Internship internship = internshipService.getById(internshipId);
        InternProfile internProfile = internProfileService.findProfileByInternId(userService.extractId());
        return internshipApplicationRepository.findInternshipApplicationByInternshipAndInternProfile(internship, internProfile);
    }

    @Override
    public List<InternshipApplication> getAllInternshipApplicationByInternProfile(InternProfile internProfile) {
        return internshipApplicationRepository.findAllByInternProfile(internProfile);
    }




}
