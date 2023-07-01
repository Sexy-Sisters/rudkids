package com.rudkids.api.verification;

import com.rudkids.core.verification.dto.VerifyRequest;
import com.rudkids.core.verification.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verify")
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationService verificationService;

    @PostMapping("/send")
    public void send(@RequestBody VerifyRequest.Send request) {
        verificationService.send(request);
    }

    @PostMapping("/validate")
    public void check(@RequestBody VerifyRequest.Check request) {
        verificationService.check(request);
    }
}
