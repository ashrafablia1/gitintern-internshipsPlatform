package com.gitintern.internships.dto;

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

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String phoneNumber;

    private String city;


}
