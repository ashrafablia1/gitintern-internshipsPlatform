package com.gitintern.internships.controllers;

import com.gitintern.internships.models.Resume;
import com.gitintern.internships.services.servicesInterfaces.ResumeService;
import com.gitintern.internships.services.servicesInterfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@AllArgsConstructor
@Controller
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping("/{resumeId}")
    public String getFile(@PathVariable Long resumeId, Model model) {
        Resume resume = resumeService.getResumeByResumeId(resumeId);
        if (resume.getData() != null) {
            model.addAttribute("resumeFound", true);
            model.addAttribute("resumeUrl", "/resume/" + resumeId + "/download");
            model.addAttribute("resumeFileName", resume.getFileName()+"."+resume.getFileType());
        } else {
            model.addAttribute("resumeFound", false);
        }
        return "resume";
    }

    @GetMapping("/{resumeId}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long resumeId) {
        Resume resume = resumeService.getResumeByResumeId(resumeId);
        if (resume.getData() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.attachment().filename(resume.getFileName()).build());
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resume.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/new")
    public String uploadNewInternResume() {
        return "upload-resume";
    }

    @PostMapping("/new")
    public String saveInternResume( @RequestParam("file") MultipartFile file) throws IOException {
        Long internId = userService.extractId();
        if (!file.isEmpty()) {
            Resume resume = new Resume();
            resume.setFileName(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setData(file.getBytes());
            resumeService.saveResume(internId, resume);
            return "redirect:/resume/" + resumeService.getResumeId(internId);
        }else {
            return "redirect:/resume/new";
        }

    }

}
