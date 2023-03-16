package com.example.confirmationtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/token")
public class ConformationTokenController {

    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public ConformationTokenController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping
    public ResponseEntity<ConfirmationResponse> createToken(@Param("userId") Integer userId,
                                              @Param("email") String email) {

        return ResponseEntity.status(201).body(confirmationTokenService.saveTokenConformation(userId, email));

    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return confirmationTokenService.confirmToken(token);
    }
}
