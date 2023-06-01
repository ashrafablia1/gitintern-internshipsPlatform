package com.gitintern.internships.dto;


import com.gitintern.internships.models.Internship;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//checked
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InternshipDto {


    // not send from frontend
    private Long internshipId;

    // not send from frontend
    private Long companyId;

    // not send from frontend
    private String companyName;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @NotEmpty(message = "Duration cannot be empty")
    private String duration;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Requirements cannot be empty")
    private String requirements;

    @NotEmpty(message = "Responsibilities cannot be empty")
    private String responsibilities;

    @NotEmpty(message = "Qualifications cannot be empty")
    private String qualifications;

    @NotEmpty(message = "Application deadline cannot be empty")
    private String applicationDeadline;

    private String contactEmail;

    @NotEmpty(message = "is Paid cannot be empty")
    private boolean isPaid;

    @NotEmpty(message = "Number of positions cannot be empty")
    private int numberOfPositions;

    @NotEmpty(message = "is Remote cannot be empty")
    private boolean isRemote;

    public InternshipDto(Internship internship) {
        this.internshipId = internship.getInternshipId();
        this.companyId = internship.getCompany().getUserId();
        this.companyName = internship.getCompany().getCompanyName();
        this.title = internship.getTitle();
        this.location = internship.getLocation();
        this.duration = internship.getDuration();
        this.description = internship.getDescription();
        this.requirements = internship.getRequirements();
        this.responsibilities = internship.getResponsibilities();
        this.qualifications = internship.getQualifications();
        this.applicationDeadline = internship.getApplicationDeadline().toString();
        this.contactEmail = internship.getContactEmail();
        this.isPaid = internship.isPaid();
        this.numberOfPositions = internship.getNumberOfPositions();
        this.isRemote = internship.isRemote();
    }
}
