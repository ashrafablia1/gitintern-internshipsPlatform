package com.gitintern.internships.services.servicesInterfaces;

import com.gitintern.internships.dto.InternshipDto;
import com.gitintern.internships.models.Internship;

import java.util.List;
//checked getInternship & getAllInternships
public interface InternshipService {

    Long createInternship(InternshipDto internshipDto);

    InternshipDto getInternship(Long internshipId);

    InternshipDto editInternship(Long internshipId, InternshipDto internshipDto);

    List<InternshipDto> getAllInternships();


    Internship getById(Long internshipId);

    List<Internship> getAllInternshipsByCompanyId(Long companyId);

}
