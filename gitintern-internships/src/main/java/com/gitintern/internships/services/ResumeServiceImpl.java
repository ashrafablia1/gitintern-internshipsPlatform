package com.gitintern.internships.services;

import com.gitintern.internships.models.Intern;
import com.gitintern.internships.models.Resume;
import com.gitintern.internships.repositories.InternRepository;
import com.gitintern.internships.repositories.ResumeRepository;
import com.gitintern.internships.services.servicesInterfaces.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

// this class implements the methods defined in the ResumeService interface
@AllArgsConstructor
@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final InternRepository internRepository;


    @Override
    public void saveResume(Long userId, Resume resume) {
        Optional<Resume> optionalResume = Optional.ofNullable(getResumeByInternId(userId));
        if (optionalResume.isPresent()) {
            Resume existResume = optionalResume.get();
            existResume.setFileName(resume.getFileName());
            existResume.setFileType(resume.getFileType());
            existResume.setData(resume.getData());
            resumeRepository.save(existResume);
        } else {
            Intern intern = internRepository.findById(userId).get();
            Resume newResume = new Resume(intern, resume.getFileName(), resume.getFileType(), resume.getData());
            resumeRepository.save(newResume);
        }
    }

    @Override
    public Resume getResumeByInternId(Long userId) {
        Intern intern = internRepository.findById(userId).get();
        Optional<Resume> resume = resumeRepository.findResumeByIntern(intern);
        return resume.get();
    }

    @Override
    public Resume getResumeByResumeId(Long resumeId) {
        return resumeRepository.findById(resumeId).orElse(null);
    }

    @Override
    public Long getResumeId(Long userId) {
        Intern intern = internRepository.findById(userId).get();
        return resumeRepository.findResumeByIntern(intern).get().getResumeId();
    }

    @Override
    public void createResume(Intern intern) {
        Resume resume = new Resume(intern);
        resumeRepository.save(resume);
    }
}
