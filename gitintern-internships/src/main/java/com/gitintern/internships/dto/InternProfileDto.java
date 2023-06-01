package com.gitintern.internships.dto;

import com.gitintern.internships.models.Intern;
import com.gitintern.internships.models.InternProfile;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternProfileDto {

    private Intern intern;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String phoneNumber;

    private String city;


    public InternProfileDto(InternProfile internProfile ) {
        this.firstName = internProfile.getFirstName();
        this.lastName = internProfile.getLastName();
        this.email = internProfile.getEmail();
        this.phoneNumber = internProfile.getPhoneNumber();
        this.city = internProfile.getCity();
    }
}
