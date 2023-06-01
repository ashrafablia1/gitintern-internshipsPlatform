package com.gitintern.internships.services.servicesInterfaces;

import com.gitintern.internships.dto.InternProfileDto;
import com.gitintern.internships.models.Intern;
import com.gitintern.internships.models.InternProfile;

// this interface used to define the methods that will be used in the InternProfileServiceImpl class
public interface InternProfileService {
    void editProfile(Long userId, InternProfileDto internProfileDto);

    InternProfileDto getProfile(Long internId);

    void createProfile(Intern newIntern);

    InternProfile findProfileByInternId(Long internId);
}
