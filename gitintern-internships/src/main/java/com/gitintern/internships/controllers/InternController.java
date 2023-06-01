package com.gitintern.internships.controllers;


import com.gitintern.internships.dto.InternProfileDto;
import com.gitintern.internships.dto.InternshipDto;
import com.gitintern.internships.models.InternProfile;
import com.gitintern.internships.models.InternshipApplication;
import com.gitintern.internships.models.Resume;
import com.gitintern.internships.services.servicesInterfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/intern")
public class InternController {

    private final UserService userService;
    private final InternProfileService internProfileService;
    private final ResumeService resumeService;
    private final InternshipService internshipService;
    private final InternshipApplicationService internshipApplicationService;

    @GetMapping("/homepage")
    public String internLoginForm(Model model) {
        model.addAttribute("internships", internshipService.getAllInternships());
        return "intern-homepage";
    }

    @GetMapping("/profile")
    public String internProfileForm() {
        Long internId = userService.extractId();
        return "redirect:/intern/profile/" + internId;

    }

    @GetMapping("/profile/{internId}")
    public String getInternProfile(@PathVariable Long internId, ModelMap model) {
        InternProfile internProfile = internProfileService.findProfileByInternId(internId);
        model.addAttribute("internProfile", internProfile);
        model.addAttribute("resumeId", resumeService.getResumeId(internId));
       List<InternshipApplication> InternInternshipApplications = internshipApplicationService.getAllInternshipApplicationByInternProfile(internProfile);
        model.addAttribute("internInternshipApplications", InternInternshipApplications);

        return "intern-profile";
    }


    @GetMapping("/profile/{internId}/edit")
    public String EditInternProfile(@PathVariable Long internId, ModelMap model) {
        InternProfileDto internProfileDto = internProfileService.getProfile(internId);
        model.addAttribute("internId", internId);
        model.addAttribute("internProfile", internProfileDto);

        return "edit-intern-profile";
    }

    @PostMapping("/profile/{internId}/edit")
    public String saveInternProfile(@PathVariable Long internId, @ModelAttribute("internProfile") InternProfileDto internProfileDto) {
        internProfileService.editProfile(internId, internProfileDto);
        return "redirect:/intern/profile/" + internId;
    }

    @GetMapping("/internship/{internshipId}/apply")
    public String applyToInternship(@PathVariable Long internshipId, Model model) {
        Long internId = userService.extractId();
       InternProfileDto internProfileDto = internProfileService.getProfile(internId);
       InternshipDto internshipDto = internshipService.getInternship(internshipId);
       long resumeId = resumeService.getResumeId(internId);
         model.addAttribute("resumeUrl", "/resume/"+ resumeId);
         model.addAttribute("uploadNewResumeUrl", "/resume/new");
         model.addAttribute("internProfile", internProfileDto);
         model.addAttribute("internshipId", internshipDto.getInternshipId());
         model.addAttribute("internshipTitle", internshipDto.getTitle());

        return "internship-apply";
    }

    @PostMapping("/internship/{internshipId}/apply")
    public String applyToInternship(@PathVariable Long internshipId, @ModelAttribute("internProfile") InternProfileDto internProfile) {
        internProfileService.editProfile(userService.extractId(), internProfile);
        internshipApplicationService.createInternshipApplication(internshipId);
        return "redirect:/intern/internship/" + internshipId +"/apply?success";
    }




}




