package com.gitintern.internships.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// this class it used to store company profile data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "intern_profile")
public class InternProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intern_profile_id")
    private Long internProfileId;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "intern_id")
    private Intern intern;

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


    public InternProfile(Intern intern) {
        this.intern = intern;
        this.firstName = intern.getFirstName();
        this.lastName = intern.getLastName();
        this.email = intern.getEmail();
    }

    public InternProfile(String firstName, String lastName, String email, String phoneNumber, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }
}
