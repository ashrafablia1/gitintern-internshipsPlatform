package com.gitintern.internships.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// this class is used to transfer data between the front end and the back end
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternDto {

    private Long userId;

    @NotEmpty(message = "Please enter valid email.")
    @Email
    private String email;

    @NotEmpty(message = "Please enter valid password.")
    private String password;

    @NotEmpty(message = "Please enter valid First Name.")
    private String firstName;

    @NotEmpty(message = "Please enter valid Last Name.")
    private String lastName;
}
