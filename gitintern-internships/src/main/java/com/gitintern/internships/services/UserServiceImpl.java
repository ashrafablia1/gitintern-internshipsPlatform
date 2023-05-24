package com.gitintern.internships.services;


import com.gitintern.internships.dto.CompanyDto;
import com.gitintern.internships.dto.InternDto;
import com.gitintern.internships.models.*;
import com.gitintern.internships.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


// this class is used to implement the methods defined in the UserService interface

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenServiceImpl confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final InternProfileService internProfileService;
    private final CompanyProfileService companyProfileService;
    private final ResumeService resumeService;

    @Override
    public void registerNewUser(InternDto internDto) {

        // create new intern object & hash user password before saving
        Intern newIntern = new Intern();
        newIntern.setEmail(internDto.getEmail());
        newIntern.setPassword(passwordEncoder.encode(internDto.getPassword()));
        newIntern.setFirstName(internDto.getFirstName());
        newIntern.setLastName(internDto.getLastName());
        newIntern.setRole(Role.INTERN);


        // save user to database, create profile, create confirmation token and send confirmation mail
        userRepository.save(newIntern);
        internProfileService.createProfile(newIntern);
        resumeService.createResume(newIntern);
        confirmationTokenService.newConfirmationToken(newIntern);
        sendConfirmationMail(internDto.getEmail(), confirmationTokenService.getUserConfirmationToken(newIntern).getConfirmationToken());


    }

    @Override
    public void registerNewUser(CompanyDto companyDto) {

        // create new Company object & hash user password before saving
        Company newUser = new Company();
        newUser.setEmail(companyDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(companyDto.getPassword()));
        newUser.setCompanyName(companyDto.getName());
        newUser.setRole(Role.COMPANY);


        // save user to database, create profile, create confirmation token and send confirmation mail
        userRepository.save(newUser);
        companyProfileService.createProfile(newUser);
        confirmationTokenService.newConfirmationToken(newUser);
        sendConfirmationMail(companyDto.getEmail(), confirmationTokenService.getUserConfirmationToken(newUser).getConfirmationToken());
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void confirmUser(UserConfirmationToken confirmationToken) {
        final User user = confirmationToken.getUser();
        if (user != null) {
            user.setEnabled(true);
            userRepository.save(user);
            confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
        }

    }


    @Override
    public void sendConfirmationMail(String userMail, String token) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Confirmation Link!");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText("Thank you for registering. Please click on the below link to activate your account."
                + "http://localhost:8080/registration/confirm?token="
                + token);
        emailSenderService.sendEmail(mailMessage);
    }


    //extractId() method is used to get the id of the user that is currently logged in
    @Override
    public Long extractId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByEmail(username);
        return user.getUserId();
    }


}
