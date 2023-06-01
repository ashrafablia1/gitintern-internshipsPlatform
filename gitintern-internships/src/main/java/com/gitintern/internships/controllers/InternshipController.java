package com.gitintern.internships.controllers;

import com.gitintern.internships.dto.InternProfileDto;
import com.gitintern.internships.dto.InternshipDto;
import com.gitintern.internships.models.*;
import com.gitintern.internships.services.servicesInterfaces.InternshipApplicationService;
import com.gitintern.internships.services.servicesInterfaces.InternshipService;
import com.gitintern.internships.services.servicesInterfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//checked getInternship for intern only I need to edit front end later
@AllArgsConstructor
@Controller
@RequestMapping("/internship")
public class InternshipController {

private final UserService userService;
private final InternshipService internshipService;
private final InternshipApplicationService internshipApplicationService;

    @GetMapping("/{internshipId}")
    public String getInternship(@PathVariable Long internshipId, Model model) {
        User currentUser = userService.extractUser();
        InternshipDto internshipDto = internshipService.getInternship(internshipId);

      if (currentUser != null) {
          if (currentUser.getRole() == Role.COMPANY) {
              if (internshipDto.getCompanyId().equals(currentUser.getUserId())) {
                  model.addAttribute("canEdit", true);
                  model.addAttribute("applicationsCount", internshipApplicationService.internshipApplicationsCount(internshipId));
              } else {
                  model.addAttribute("canEdit", false);
              }
              model.addAttribute("isIntern", false);
              model.addAttribute("notUser", false);


          } else if (currentUser.getRole() == Role.INTERN) {
              InternshipApplication internInternshipApplication = internshipApplicationService.getInternInternshipApplicationIdByInternshipId(internshipId);
              if (internInternshipApplication != null){
                  model.addAttribute("canApply", false);
                  model.addAttribute("haveApplication", true);
                  model.addAttribute("InternInternshipApplicationId", internInternshipApplication.getId());
              } else {
                  model.addAttribute("haveApplication", false);
                    model.addAttribute("canApply", true);
              }
              model.addAttribute("isIntern", true);
              model.addAttribute("canEdit", false);
              model.addAttribute("notUser", false);
          }
      } else {
            model.addAttribute("notUser", true);
            model.addAttribute("isIntern", false);
            model.addAttribute("canEdit", false);
          }

        model.addAttribute("internship", internshipDto);

        return "internship-view";
    }


    @GetMapping("/all")
    public String getAllInternships(Model model) {
        model.addAttribute("internships", internshipService.getAllInternships());
        return "all-internships";
    }


    @GetMapping("/application/{internshipApplicationId}")
    public String getInternshipApplication(@PathVariable Long internshipApplicationId, Model model) {
        InternshipApplication internshipApplication = internshipApplicationService.getInternshipApplication(internshipApplicationId);
        model.addAttribute("internshipApplication", internshipApplication);
        InternshipDto internshipDto = internshipService.getInternship(internshipApplication.getInternship().getInternshipId());
        model.addAttribute("internshipTitle", internshipDto.getTitle());
        model.addAttribute("resumeUrl", "/resume/"+internshipApplication.getResume().getResumeId());
        if (userService.extractUser().getRole() == Role.COMPANY) {
        if (userService.extractUser().getUserId().equals(internshipDto.getCompanyId())) {
            model.addAttribute("canEdit", true);
            model.addAttribute("AcceptedIntern", false);
            model.addAttribute("isIntern", false);
        } else {
            model.addAttribute("canEdit", false);
            model.addAttribute("AcceptedIntern", false);
            model.addAttribute("isIntern", false);
        }
    } else if (userService.extractUser().getRole() == Role.INTERN){
            model.addAttribute("isIntern", true);
            if (internshipApplication.getStatus() == Status.ACCEPTED) {
                model.addAttribute("AcceptedIntern", true);
            } else {
                model.addAttribute("AcceptedIntern", false);
                model.addAttribute("canEdit", false);
            }
        }

        return "internship-application-view";
    }



}



