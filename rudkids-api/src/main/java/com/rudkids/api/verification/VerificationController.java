package com.rudkids.api.verification;

import com.rudkids.api.auth.AuthorizationExtractor;
import com.rudkids.core.auth.service.AuthService;
import com.rudkids.core.verification.dto.VerifyRequest;
import com.rudkids.core.verification.service.VerificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verify")
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationService verificationService;
    private final AuthService authService;

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody VerifyRequest.Send request) {
        verificationService.send(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate")
    public ResponseEntity<Void> check(
        HttpServletRequest httpRequest,
        @RequestBody VerifyRequest.Check request
    ) {
        var phoneNumber = verificationService.check(request);
        var accessToken = AuthorizationExtractor.extract(httpRequest);
        authService.saveAuthenticatedPhoneNumber(accessToken, phoneNumber);
        return ResponseEntity.ok().build();
    }
}
