package com.gitintern.internships.models;


import com.gitintern.internships.dto.InternshipDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//checked
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internship_id")
    private Long internshipId;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "location")
    private String location;

    @Column(name = "duration")
    private String duration;

    @Column(name = "description")
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "qualifications")
    private String qualifications;

    @Column(name = "application_deadline")
    private Date applicationDeadline;

    @Column(name = "contact_email") // can be null
    private String contactEmail;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Column(name = "number_of_positions")
    private int numberOfPositions;

    @Column(name = "is_remote")
    private boolean isRemote;


    public Internship(InternshipDto internshipDto, Company company) {
        this.company = company;
        this.title = internshipDto.getTitle();
        this.location = internshipDto.getLocation();
        this.duration = internshipDto.getDuration();
        this.description = internshipDto.getDescription();
        this.requirements = internshipDto.getRequirements();
        this.responsibilities = internshipDto.getResponsibilities();
        this.qualifications = internshipDto.getQualifications();
        this.contactEmail = internshipDto.getContactEmail();
        this.isPaid = internshipDto.isPaid();
        this.numberOfPositions = internshipDto.getNumberOfPositions();
        this.isRemote = internshipDto.isRemote();
        // convert the string to date and set it
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.applicationDeadline = dateFormat.parse(internshipDto.getApplicationDeadline());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
