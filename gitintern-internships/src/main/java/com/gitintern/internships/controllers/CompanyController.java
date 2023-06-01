package com.gitintern.internships.controllers;


import com.gitintern.internships.dto.InternshipDto;
import com.gitintern.internships.models.CompanyProfile;
import com.gitintern.internships.models.Internship;
import com.gitintern.internships.models.InternshipApplication;
import com.gitintern.internships.services.servicesInterfaces.CompanyProfileService;
import com.gitintern.internships.services.servicesInterfaces.InternshipApplicationService;
import com.gitintern.internships.services.servicesInterfaces.InternshipService;
import com.gitintern.internships.services.servicesInterfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final UserService userService;
    private final CompanyProfileService companyProfileService;
    private final InternshipService internshipService;
    private final InternshipApplicationService internshipApplicationService;


    @GetMapping("/homepage")
    public String CompanyLoginForm(Model model) {
        model.addAttribute("internships", internshipService.getAllInternships());

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
        List<Internship> companyInternships = internshipService.getAllInternshipsByCompanyId(companyId);
        model.addAttribute("companyInternships", companyInternships);
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

    @GetMapping("/posting/internship")
    public String createInternshipForm(Model model) {
        model.addAttribute("internshipDto", new InternshipDto());
        return "internship-create";
    }

    @PostMapping("/posting/internship")
    public String createInternship(@ModelAttribute("internshipDto") InternshipDto internshipDto) {

        Long internshipId = internshipService.createInternship(internshipDto);

        return "redirect:/internship/" + internshipId;
    }


    @GetMapping("/internship/edit/{internshipId}")
    public String editInternshipForm(@PathVariable Long internshipId, Model model) {
        InternshipDto internship = internshipService.getInternship(internshipId);
        model.addAttribute("internship", internship);
        if (internship.getCompanyId().equals(userService.extractId())) {
            return "internship-edit";
        } else {
            return "redirect:/internship/" + internshipId;
        }
    }

    @PostMapping("/internship/edit/{internshipId}")
    public String editInternship(@PathVariable Long internshipId, @ModelAttribute("internship") InternshipDto internshipDto) {
        internshipService.editInternship(internshipId, internshipDto);
        return "redirect:/internship/" + internshipId;
    }


    @GetMapping("/internship/applications/{internshipId}")
    public String getInternshipApplications(@PathVariable Long internshipId, Model model) {
        InternshipDto internship = internshipService.getInternship(internshipId);

        if (internship.getCompanyId().equals(userService.extractId())) {
            model.addAttribute("internshipId", internshipId);
            model.addAttribute("internshipTitle", internship.getTitle());
            model.addAttribute("internshipApplications", internshipApplicationService.getAllInternshipApplicationByInternshipId(internshipId));


            return "internship-applications";
        } else {
            return "redirect:/internship/" + internshipId;
        }
    }



    @PostMapping("/application/{internshipApplicationId}/status")
    public String changeApplicationStatus(@PathVariable Long internshipApplicationId, @RequestParam String status) {
        internshipApplicationService.changeStatus(internshipApplicationId, status);
        return "redirect:/company/internship/applications/" + internshipApplicationService.getInternshipApplication(internshipApplicationId).getInternship().getInternshipId();
    }



}




