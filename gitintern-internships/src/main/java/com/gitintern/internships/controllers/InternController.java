package com.gitintern.internships.controllers;


import com.gitintern.internships.dto.InternProfileDto;
import com.gitintern.internships.models.InternProfile;
import com.gitintern.internships.models.Resume;
import com.gitintern.internships.services.InternProfileService;
import com.gitintern.internships.services.ResumeService;
import com.gitintern.internships.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@AllArgsConstructor
@RequestMapping("/intern")
public class InternController {

    private final UserService userService;
    private final InternProfileService internProfileService;
    private final ResumeService resumeService;

    @GetMapping("/homepage")
    public String internLoginForm() {
        return "intern-homepage";
    }

    @GetMapping("/profile")
    public String internProfileForm() {
        Long internId = userService.extractId();
        return "redirect:/intern/profile/" + internId;

    }

    @GetMapping("/profile/{internId}")
    public String getInternProfile(@PathVariable Long internId, ModelMap model) {
        InternProfile internProfile = internProfileService.getProfile(internId);
        model.addAttribute("internProfile", internProfile);
        return "intern-profile";
    }


    @GetMapping("/profile/{internId}/edit")
    public String EditInternProfile(@PathVariable Long internId, ModelMap model) {
        InternProfile internProfile = internProfileService.getProfile(internId);
        model.addAttribute("internProfile", internProfile);

        return "edit-intern-profile";
    }

    @PostMapping("/profile/{internId}/edit")
    public String saveInternProfile(@PathVariable Long internId, @ModelAttribute InternProfileDto internProfileDto) {
        internProfileService.editProfile(internId, internProfileDto);
        return "redirect:/intern/profile/" + internId;
    }

    @GetMapping("/{internId}/resume")
    public ResponseEntity<byte[]> getFile(@PathVariable Long internId) {
        Resume resume = resumeService.getResume(internId).get();
        if (resume.getData() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resume.getFileName() + "\"")
                    .body(resume.getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resume found for this intern".getBytes());
        }
    }


    @GetMapping("/{internId}/resume/new")
    public String uploadNewInternResume(@PathVariable Long internId, ModelMap model) {
        model.addAttribute("internId", internId);
        return "upload-resume";
    }

    @PostMapping("/{internId}/resume/new")
    public String saveInternResume(@PathVariable Long internId, @ModelAttribute("resume") Resume resume, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            resume.setFileName(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setData(file.getBytes());
            resumeService.saveResume(internId, resume);
        }
        return "redirect:/intern/" + internId + "/resume/new?success";
    }
}




