package com.otpless.javaotplessdemo.controller;

import com.otpless.javaotplessdemo.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/auth/")
public class APIAuthController {

    private final AuthService authService;

    public APIAuthController(AuthService authService) {
        this.authService = authService;
    }

    private ResponseEntity<Object> handleException(Exception e, String action) {
        log.error("Error occurred while trying to {}: {}", action, e.getMessage(), e);
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return ResponseEntity.status(500).body(response);
    }

    @GetMapping(value = "/initiate/otp/phoneNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendOTPToPhoneNumber(@RequestParam String phoneNumber, @RequestParam(required = false) Integer otpLength) {
        try {
            return ResponseEntity.ok(authService.initiateOTPOnPhoneNumber(phoneNumber, otpLength));
        } catch (Exception e) {
            return handleException(e, "send OTP to phone number");
        }
    }

    @GetMapping(value = "/initiate/otp/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendOTPToEmail(@RequestParam String email, @RequestParam(required = false) Integer otpLength) {
        try {
            return ResponseEntity.ok(authService.initiateOTPOnEmail(email, otpLength));
        } catch (Exception e) {
            return handleException(e, "send OTP to email");
        }
    }

    @GetMapping(value = "/verify/otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> verifyOTP(@RequestParam String otp, @RequestParam String requestId) {
        try {
            return ResponseEntity.ok(authService.verifyOTP(requestId, otp));
        } catch (Exception e) {
            return handleException(e, "verify OTP");
        }
    }

    @GetMapping(value = "/initiate/magiclink/phoneNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendMagicLinkToPhoneNumber(@RequestParam String phoneNumber, @RequestParam String redirectURI) {
        try {
            return ResponseEntity.ok(authService.initiateMagicLinkToPhoneNumber(phoneNumber, redirectURI));
        } catch (Exception e) {
            return handleException(e, "send magic link to phone number");
        }
    }

    @GetMapping(value = "/initiate/magiclink/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendMagicLinkToEmail(@RequestParam String email, @RequestParam String redirectURI) {
        try {
            return ResponseEntity.ok(authService.initiateMagicLinkToEmail(email, redirectURI));
        } catch (Exception e) {
            return handleException(e, "send magic link to email");
        }
    }

    @GetMapping(value = "/initiate/otplink/phoneNumber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendMagicLinkAndOTPToPhoneNumber(@RequestParam String phoneNumber, @RequestParam String redirectURI) {
        try {
            return ResponseEntity.ok(authService.initiateOTPLinkToPhoneNumber(phoneNumber, redirectURI));
        } catch (Exception e) {
            return handleException(e, "send magic link and OTP to phone number");
        }
    }

    @GetMapping(value = "/initiate/otplink/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendMagicLinkAndOTPToEmail(@RequestParam String email, @RequestParam String redirectURI) {
        try {
            return ResponseEntity.ok(authService.initiateOTPLinkToEmail(email, redirectURI));
        } catch (Exception e) {
            return handleException(e, "send magic link and OTP to email");
        }
    }

    @GetMapping(value = "/initiate/oauth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> initiateOAuth(@RequestParam String channel, @RequestParam String redirectURI) {
        try {
            return ResponseEntity.ok(authService.initiateOAuth(channel, redirectURI));
        } catch (Exception e) {
            return handleException(e, "initiate OAuth");
        }
    }

    @GetMapping(value = "/verify/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> verifyCode(@RequestParam String code) {
        try {
            return ResponseEntity.ok(authService.verifyCode(code));
        } catch (Exception e) {
            return handleException(e, "verify code");
        }
    }

    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> status(@RequestParam String requestId) {
        try {
            return ResponseEntity.ok(authService.status(requestId));
        } catch (Exception e) {
            return handleException(e, "get authentication status");
        }
    }
}
