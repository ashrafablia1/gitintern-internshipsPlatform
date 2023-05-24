package com.gitintern.internships.services;

import com.gitintern.internships.models.Company;
import com.gitintern.internships.models.CompanyProfile;

// this interface used to define the methods that will be used in the CompanyProfileServiceImpl class
public interface CompanyProfileService {

    void editProfile(Long userId, CompanyProfile companyProfile);

    CompanyProfile getProfile(Long userId);

    void createProfile(Company newCompany);

    CompanyProfile findProfileByCompanyId(Long companyId);


}
