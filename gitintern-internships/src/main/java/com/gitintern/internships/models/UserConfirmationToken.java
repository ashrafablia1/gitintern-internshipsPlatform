package com.gitintern.internships.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

// this class is used to store the token generated for the user
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_token")
public class UserConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    //Generate a new token for the user
    public UserConfirmationToken(User user) {
        this.user = user;
        this.createdDate = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }


}
