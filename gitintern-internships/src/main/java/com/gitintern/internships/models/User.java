package com.gitintern.internships.models;

import jakarta.persistence.*;
import lombok.*;


//this class parent for all users
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    // user is not enabled by default. should verify email to enable
    @Column(name = "enabled")
    private Boolean enabled = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}
