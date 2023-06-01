package com.gitintern.internships.services.servicesInterfaces;


import com.gitintern.internships.dto.CompanyDto;
import com.gitintern.internships.dto.InternDto;
import com.gitintern.internships.models.Company;
import com.gitintern.internships.models.User;
import com.gitintern.internships.models.UserConfirmationToken;


// this interface used to define the methods that will be used in the UserServiceImpl class
public interface UserService {


    void registerNewUser(InternDto internDto);


    void registerNewUser(CompanyDto companyDto);

    User findUserByEmail(String email);

    void confirmUser(UserConfirmationToken confirmationToken);

    void sendConfirmationMail(String userMail, String token);

    Long extractId();

    User extractUser();

    User findById(Long userId);
}
