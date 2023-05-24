package com.gitintern.internships.controllers;


import com.gitintern.internships.models.CompanyProfile;
import com.gitintern.internships.services.CompanyProfileService;
import com.gitintern.internships.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final UserService userService;
    private final CompanyProfileService companyProfileService;


    @GetMapping("/homepage")
    public String CompanyLoginForm() {
        return "company-homepage";
    }


    @GetMapping("/profile")
    public String internProfileForm() {
        Long companyId = userService.extractId();
        return "redirect:/company/profile/" + companyId;

    }

    @GetMapping("/profile/{companyId}")
    public String getCompanyProfile(@PathVariable Long companyId, ModelMap model) {
        CompanyProfile companyProfile = companyProfileService.getProfile(companyId);
        model.addAttribute("companyProfile", companyProfile);
        return "company-profile";
    }


    @GetMapping("/profile/{companyId}/edit")
    public String EditCompanyProfile(@PathVariable Long companyId, ModelMap model) {
        CompanyProfile companyProfile = companyProfileService.getProfile(companyId);
        model.addAttribute("companyProfile", companyProfile);

        return "edit-company-profile";
    }

    @PostMapping("/profile/{companyId}/edit")
    public String saveInternProfile(@PathVariable Long companyId, @ModelAttribute CompanyProfile companyProfile) {

        companyProfileService.editProfile(companyId, companyProfile);
        return "redirect:/company/profile/" + companyId;
    }


}

