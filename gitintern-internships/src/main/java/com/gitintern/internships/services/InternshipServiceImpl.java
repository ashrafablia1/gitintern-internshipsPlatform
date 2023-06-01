package com.gitintern.internships.services;

import com.gitintern.internships.dto.InternshipDto;
import com.gitintern.internships.models.Company;
import com.gitintern.internships.models.Internship;
import com.gitintern.internships.repositories.InternshipRepository;
import com.gitintern.internships.services.servicesInterfaces.InternshipService;
import com.gitintern.internships.services.servicesInterfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
//checked getInternship & getAllInternships
@AllArgsConstructor
@Service
public class InternshipServiceImpl implements InternshipService {

    private final UserService userService;
    private final InternshipRepository internshipRepository;

    @Override
    public Long createInternship(InternshipDto internshipDto) {

        // find the company that is currently logged in
        Company company = (Company) userService.extractUser();
        // create the internship object
        Internship internship = new Internship(internshipDto, company);

        // save the internship object to the database
        internshipRepository.save(internship);

        // return the id of the internship
        return internship.getInternshipId();
    }

    @Override
    public InternshipDto getInternship(Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new RuntimeException("Internship not found"));

        return new InternshipDto(internship);
    }

    @Override
    public InternshipDto editInternship(Long internshipId, InternshipDto internshipDto) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new RuntimeException("Internship not found"));
        internship.setTitle(internshipDto.getTitle());
        internship.setLocation(internshipDto.getLocation());
        internship.setDuration(internshipDto.getDuration());
        internship.setDescription(internshipDto.getDescription());
        internship.setRequirements(internshipDto.getRequirements());
        internship.setResponsibilities(internshipDto.getResponsibilities());
        internship.setQualifications(internshipDto.getQualifications());
        internship.setContactEmail(internshipDto.getContactEmail());
        internship.setPaid(internshipDto.isPaid());
        internship.setNumberOfPositions(internshipDto.getNumberOfPositions());
        internship.setRemote(internshipDto.isRemote());
        // convert the string to date and set it
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            internship.setApplicationDeadline(dateFormat.parse(internshipDto.getApplicationDeadline()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // save the internship object to the database
        internshipRepository.save(internship);

        // return the id of the internship
        return internshipDto;
    }

    @Override
    public List<InternshipDto> getAllInternships() {
        List<Internship> internships = internshipRepository.findAll();
        List<InternshipDto> internshipDtos = new ArrayList<>();
        for (Internship internship : internships) {
            InternshipDto internshipDto = new InternshipDto(internship);
            internshipDtos.add(internshipDto);
         }
        Collections.reverse(internshipDtos);
        return internshipDtos;
    }

    @Override
    public Internship getById(Long internshipId) {
        return internshipRepository.findById(internshipId).orElse(null);
    }

    @Override
    public List<Internship> getAllInternshipsByCompanyId(Long companyId) {
        Company company = (Company) userService.findById(companyId);
        List<Internship> internships = internshipRepository.findAllInternshipsByCompany(company);
        Collections.reverse(internships);
        return internships;
    }


}
