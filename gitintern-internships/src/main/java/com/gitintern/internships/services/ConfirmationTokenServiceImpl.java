package com.gitintern.internships.services;


import com.gitintern.internships.models.User;
import com.gitintern.internships.models.UserConfirmationToken;
import com.gitintern.internships.repositories.UserConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl {
    private final UserConfirmationTokenRepository userconfirmationTokenRepository;

    void newConfirmationToken(User user) {
        UserConfirmationToken confirmationToken = new UserConfirmationToken(user);
        userconfirmationTokenRepository.save(confirmationToken);
    }

    void deleteConfirmationToken(Long id) {
        userconfirmationTokenRepository.deleteById(id);
    }

    public Optional<UserConfirmationToken> findConfirmationTokenByToken(String token) {
        return userconfirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
    }

    public UserConfirmationToken getUserConfirmationToken(User user) {
        return userconfirmationTokenRepository.findConfirmationTokenByUser(user);
    }


}
