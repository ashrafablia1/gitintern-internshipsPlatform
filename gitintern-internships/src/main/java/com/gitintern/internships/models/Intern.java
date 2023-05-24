package com.gitintern.internships.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// this class is child of User class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("INTERN")
public class Intern extends User {


    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;
}
