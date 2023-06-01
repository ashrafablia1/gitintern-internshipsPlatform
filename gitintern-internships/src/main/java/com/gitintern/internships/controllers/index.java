package com.gitintern.internships.controllers;

import com.gitintern.internships.services.servicesInterfaces.InternshipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class index {

    private final InternshipService internshipService;

    @GetMapping("/")
    public String home(Model model) {
     model.addAttribute("internships", internshipService.getAllInternships());

     return "index";
    }

}
