package com.gitintern.internships.services;

import com.gitintern.internships.models.Company;
import com.gitintern.internships.models.CompanyProfile;
import com.gitintern.internships.repositories.CompanyProfileRepository;
import com.gitintern.internships.repositories.CompanyRepository;
import com.gitintern.internships.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//this class is used to implement the methods in the CompanyProfileService interface
@AllArgsConstructor
@Service
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private CompanyProfileRepository companyProfileRepository;
    private CompanyRepository companyRepository;
    private UserRepository userRepository;


    @Override
    public CompanyProfile getProfile(Long companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            Optional<CompanyProfile> optionalCompanyProfile = Optional.ofNullable(findProfileByCompanyId(companyId));
            return optionalCompanyProfile.orElseGet(() -> companyProfileRepository.save(new CompanyProfile(company)));
        }
        throw new UsernameNotFoundException("Company not found");
    }

    @Override
    public void editProfile(Long companyId, CompanyProfile companyProfile) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            CompanyProfile existCompanyProfile = findProfileByCompanyId(companyId);

            if (existCompanyProfile.getCompanyName() != null) {
                company.setCompanyName(companyProfile.getCompanyName());
                existCompanyProfile.setCompanyName(companyProfile.getCompanyName());
            }
            if (companyProfile.getEmail() != null) {
                company.setEmail(companyProfile.getEmail());
                existCompanyProfile.setEmail(companyProfile.getEmail());
            }
            if (companyProfile.getCompanyLink() != null) {
                existCompanyProfile.setCompanyLink(companyProfile.getCompanyLink());
            }
            if (companyProfile.getPhoneNumber() != null)
                existCompanyProfile.setPhoneNumber(companyProfile.getPhoneNumber());
            if (companyProfile.getCity() != null)
                existCompanyProfile.setCity(companyProfile.getCity());
            existCompanyProfile.setCompany(company);
            companyRepository.save(company);
            companyProfileRepository.save(existCompanyProfile);

        }
        throw new UsernameNotFoundException("Company not found");
    }


    @Override
    public void createProfile(Company newCompany) {
        CompanyProfile companyProfile = new CompanyProfile(newCompany);
        companyProfileRepository.save(companyProfile);
    }

    @Override
    public CompanyProfile findProfileByCompanyId(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            return companyProfileRepository.findProfileByCompany(company.get());

        }
        throw new UsernameNotFoundException("profile not found");
    }


}
