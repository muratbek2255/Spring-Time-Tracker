package com.example.confirmationtoken;


import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final EmailSenderServiceImpl emailSenderService;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository, EmailSenderServiceImpl emailSenderService) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    @SneakyThrows
    public ConfirmationResponse saveTokenConformation(Integer userId, String email) {

        String randomToken = UUID.randomUUID().toString();

        ConfirmationToken token = new ConfirmationToken(
                randomToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userId,
                email
        );

        confirmationTokenRepository.save(token);

        ConfirmationResponse confirmationResponse = new ConfirmationResponse();

        confirmationResponse.setToken(randomToken);

        return confirmationResponse;
    }

    public int enableAppUser(String email) {
        return confirmationTokenRepository.enableAppUser(email);
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        enableAppUser(
                confirmationToken.getEmail());
        return "confirmed";
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
