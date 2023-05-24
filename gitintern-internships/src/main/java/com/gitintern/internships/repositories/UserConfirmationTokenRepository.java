package com.gitintern.internships.repositories;


import com.gitintern.internships.models.User;
import com.gitintern.internships.models.UserConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// this interface is used to access the database
@Repository
public interface UserConfirmationTokenRepository extends JpaRepository<UserConfirmationToken, Long> {
    Optional<UserConfirmationToken> findConfirmationTokenByConfirmationToken(String token);

    UserConfirmationToken findConfirmationTokenByUser(User user);
}
