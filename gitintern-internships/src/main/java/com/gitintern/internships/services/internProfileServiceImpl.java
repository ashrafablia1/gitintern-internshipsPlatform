package com.gitintern.internships.services;

import com.gitintern.internships.dto.InternProfileDto;
import com.gitintern.internships.models.Intern;
import com.gitintern.internships.models.InternProfile;
import com.gitintern.internships.repositories.InternProfileRepository;
import com.gitintern.internships.repositories.InternRepository;
import com.gitintern.internships.services.servicesInterfaces.InternProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// this class used to implements the methods in the InternProfileService interface
@AllArgsConstructor
@Service
public class internProfileServiceImpl implements InternProfileService {

    private final InternProfileRepository internProfileRepository;

    private final InternRepository internRepository;


    @Override
    public InternProfileDto getProfile(Long internId) {
        Optional<Intern> optionalIntern = internRepository.findById(internId);
        if (optionalIntern.isPresent()) {
            Intern intern = optionalIntern.get();
            Optional<InternProfile> optionalInternProfile = Optional.ofNullable(findProfileByInternId(internId));
            if (optionalInternProfile.isPresent()) {
                InternProfile internProfile = optionalInternProfile.get();
                return new InternProfileDto(internProfile);
            } else {
                createProfile(intern);
                return new InternProfileDto(findProfileByInternId(internId));
            }

        }
        throw new UsernameNotFoundException("Intern not found");
    }

    @Override
    public void editProfile(Long internId, InternProfileDto internProfileDto) {
        InternProfile internProfile = new InternProfile();
        internProfile.setFirstName(internProfileDto.getFirstName());
        internProfile.setLastName(internProfileDto.getLastName());
        internProfile.setEmail(internProfileDto.getEmail());
        internProfile.setPhoneNumber(internProfileDto.getPhoneNumber());
        internProfile.setCity(internProfileDto.getCity());


        Optional<Intern> optionalIntern = internRepository.findById(internId);
        if (optionalIntern.isPresent()) {
            Intern intern = optionalIntern.get();
            InternProfile existInternProfile = findProfileByInternId(internId);
            if (internProfile.getFirstName() != null) {
                intern.setFirstName(internProfile.getFirstName());
                existInternProfile.setFirstName(internProfile.getFirstName());
            }
            if (internProfile.getLastName() != null) {
                intern.setLastName(internProfile.getLastName());
                existInternProfile.setLastName(internProfile.getLastName());
            }
            if (internProfile.getEmail() != null) {
                intern.setEmail(internProfile.getEmail());
                existInternProfile.setEmail(internProfile.getEmail());
            }
            if (internProfile.getPhoneNumber() != null)
                existInternProfile.setPhoneNumber(internProfile.getPhoneNumber());
            if (internProfile.getCity() != null)
                existInternProfile.setCity(internProfile.getCity());
            existInternProfile.setIntern(intern);
            internRepository.save(intern);
            internProfileRepository.save(existInternProfile);

        }
    }


    public void createProfile(Intern intern) {
        InternProfile internProfile = new InternProfile(intern);
        internProfileRepository.save(internProfile);
    }

    @Override
    public InternProfile findProfileByInternId(Long internId) {
        Optional<Intern> intern = internRepository.findById(internId);
        if (intern.isPresent()) {
            return internProfileRepository.findProfileByIntern(intern.get());

        }
        throw new UsernameNotFoundException("profile not found");

    }
}
