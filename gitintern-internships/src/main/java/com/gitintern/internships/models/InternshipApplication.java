package com.gitintern.internships.models;

import com.gitintern.internships.dto.InternProfileDto;
import com.gitintern.internships.dto.InternshipDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "internship_application")
public class InternshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intern_profile_id")
    private InternProfile internProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    public InternshipApplication( Internship internship, InternProfile internProfile, Resume resume) {
        this.internship = internship;
        this.internProfile = internProfile;
        this.resume = resume;
        this.firstName = internProfile.getFirstName();
        this.lastName = internProfile.getLastName();
        this.email = internProfile.getEmail();
        this.phoneNumber = internProfile.getPhoneNumber();
        this.city = internProfile.getCity();
        this.status = Status.PENDING;

    }




}

