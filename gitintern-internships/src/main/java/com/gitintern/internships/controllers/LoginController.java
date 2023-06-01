package com.gitintern.internships.controllers;


import com.gitintern.internships.dto.CompanyDto;
import com.gitintern.internships.dto.InternDto;
import com.gitintern.internships.models.User;
import com.gitintern.internships.models.UserConfirmationToken;
import com.gitintern.internships.services.ConfirmationTokenServiceImpl;
import com.gitintern.internships.services.servicesInterfaces.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userService;
    private final ConfirmationTokenServiceImpl confirmationTokenService;


    // one login form for both company and intern
    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }


    @GetMapping("/registration/interns")
    public String registrationForm(Model model) {
        InternDto intern = new InternDto();
        model.addAttribute("user", intern);
        return "registration-intern";
    }

    @PostMapping("/registration/interns")
    public String registration(@Valid @ModelAttribute("user") InternDto internDto,
                               BindingResult result, Model model) {
        User existingIntern = userService.findUserByEmail(internDto.getEmail());

        if (existingIntern != null) {
            result.rejectValue("email", null, "Intern already registered");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", internDto);
            return "registration-intern";
        }
        userService.registerNewUser(internDto);

        return "redirect:/registration/interns?success";
    }


    @GetMapping("/registration/confirm")
    String confirmMail(@RequestParam("token") String token) {
        Optional<UserConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
        ;

        if (optionalConfirmationToken.isPresent()) {
            UserConfirmationToken confirmationToken = optionalConfirmationToken.get();
            userService.confirmUser(confirmationToken);
        }

        return "redirect:/login";
    }


    // company registration


    @GetMapping("/registration/companies")
    public String companyRegistrationForm(Model model) {
        CompanyDto user = new CompanyDto();
        model.addAttribute("company", user);
        return "company-registration";
    }

    @PostMapping("/registration/companies")
    public String companyRegistration(@Valid @ModelAttribute("company") CompanyDto companyDto,
                                      BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(companyDto.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null, "User already registered");
        }

        if (result.hasErrors()) {
            model.addAttribute("company", companyDto);
            return "company-registration";
        }
        userService.registerNewUser(companyDto);

        return "redirect:/registration/companies?success";
    }


}
