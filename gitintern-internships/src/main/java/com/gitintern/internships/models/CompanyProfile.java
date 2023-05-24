
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
@Table(name = "company_profile")
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_profile_id")
    private Long companyProfileId;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email")
    private String email;

    @Column(name = "company_link")
    private String companyLink;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    public CompanyProfile(Company company) {
        this.company = company;
        this.companyName = company.getCompanyName();
        this.email = company.getEmail();
    }

}
