package com.otpless.javaotplessdemo.services;

import com.otpless.authsdk.dto.*;
import com.otpless.authsdk.dto.auth.AuthDetails;
import com.otpless.authsdk.dto.auth.VerifyCodeResponse;
import com.otpless.authsdk.service.OTPLessAPIAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class AuthService {

    private static final Integer DEFAULT_EXPIRATION_TIME = 300;
    private final OTPLessAPIAuth otpLessAuth;

    public AuthService(@Value("${otpless.client.id}") String clientId, @Value("${otpless.client.secret}") String clientSecret) {
        this.otpLessAuth = new OTPLessAPIAuth(clientId, clientSecret);
    }


    public InitiateAuthResponse initiateOTPOnPhoneNumber(String phoneNumber, Integer otpLength) {
        try {
            OTPPayload payload = OTPPayload.builder()
                    .phoneNumber(phoneNumber)
                    .otpLength(otpLength) //Optional. Default value is 6 and allowed value are 6 and 4
                    .channels(Arrays.asList("WHATSAPP", "SMS", "VIBER")) //Optional. Default will be pick from your config which is configured on Dashboard
                    .expiry(DEFAULT_EXPIRATION_TIME) //Optional Default is 5 min
                    .build();
            InitiateAuthResponse initiateAuthResponse = otpLessAuth.sendOTP(payload);

            if (initiateAuthResponse.isSuccess()) {
                log.info("OTP successfully sent. Request Id:  {}", initiateAuthResponse.getRequestId());
            } else {
                log.info("OTP Failed due to {}", initiateAuthResponse.getErrorDescription());
            }
            return initiateAuthResponse;
        } catch (Exception e) {
            log.error("Error initiating OTP on Phone Number: {}", e.getMessage());
            throw new RuntimeException("Error initiating OTP on Phone Number", e);
        }
    }

    public InitiateAuthResponse initiateOTPOnEmail(String email, Integer otpLength) {
        try {

            OTPPayload payload = OTPPayload.builder()
                    .email(email)
                    .otpLength(otpLength) //Optional. Default value is 6 and allowed value are 6 and 4
                    .expiry(DEFAULT_EXPIRATION_TIME) //Optional Default is 5 min
                    .build();

            InitiateAuthResponse initiateAuthResponse = otpLessAuth.sendOTP(payload);

            if (initiateAuthResponse.isSuccess()) {
                log.info("Email OTP successfully sent. Request Id:  {}", initiateAuthResponse.getRequestId());
            } else {
                log.info("Email OTP Failed due to {}", initiateAuthResponse.getErrorMessage());
            }
            return initiateAuthResponse;
        } catch (Exception e) {
            log.error("Error initiating OTP on Email: {}", e.getMessage());
            throw new RuntimeException("Error initiating OTP on Email", e);
        }
    }

    public VerifyOTPResponse verifyOTP(String requestId, String otp) {
        try {

            OTPPayload payload = OTPPayload.builder()
                    .otp(otp)
                    .requestId(requestId)
                    .build();

            VerifyOTPResponse verifyOTPResponse = otpLessAuth.verifyOTP(payload);

            if (verifyOTPResponse.isSuccess()) {
                if (Boolean.TRUE.equals(verifyOTPResponse.getIsOTPVerified())) {
                    log.info("OTP successfully verified. Request Id:  {}", verifyOTPResponse.getRequestId());
                } else {
                    log.info("OTP Verification failed due to {}", verifyOTPResponse.getMessage());
                }
            } else {
                log.info("API Failed due to {}", verifyOTPResponse.getErrorMessage());
            }
            return verifyOTPResponse;
        } catch (Exception e) {
            log.error("Error verifying OTP: {}", e.getMessage());
            throw new RuntimeException("Error verifying OTP", e);
        }
    }


    public InitiateAuthResponse initiateOAuth(String channel, String redirectURI) {
        try {

            OAuthPayload payload = OAuthPayload.builder()
                    .channel(channel)
                    .redirectURI(redirectURI)
                    .expiry(DEFAULT_EXPIRATION_TIME)
                    .build();
            InitiateAuthResponse initiateAuthResponse = otpLessAuth.initiateOAuth(payload);

            if (initiateAuthResponse.isSuccess()) {
                log.info("OAuth Initiated successfully. Request Id:  {}", initiateAuthResponse.getRequestId());
                //Redirect User to initiateAuthResponse.getLink()
            } else {
                log.info("OAuth Failed due to {}", initiateAuthResponse.getErrorMessage());
            }
            return initiateAuthResponse;
        } catch (Exception e) {
            log.error("Error initiating OAuth on {}: {}", channel, e.getMessage());
            throw new RuntimeException("Error initiating OAuth", e);
        }
    }

    public InitiateAuthResponse initiateMagicLinkToPhoneNumber(String phoneNumber, String redirectURI) {
        try {
            MagicLinkPayload payload = MagicLinkPayload.builder()
                    .phoneNumber(phoneNumber)
                    .channels(Arrays.asList("WHATSAPP", "SMS", "VIBER"))//Optional
                    .redirectURI(redirectURI)
                    .expiry(DEFAULT_EXPIRATION_TIME) //Optional Default is 5 min.
                    .build();

            InitiateAuthResponse initiateAuthResponse = otpLessAuth.sendMagicLink(payload);

            if (initiateAuthResponse.isSuccess()) {
                log.info("Magic Link Initiated successfully. Request Id:  {}", initiateAuthResponse.getRequestId());
                //Redirect User to initiateAuthResponse.getLink()
            } else {
                log.info("Magic Link Failed due to {}", initiateAuthResponse.getErrorMessage());
            }
            return initiateAuthResponse;
        } catch (Exception e) {
            log.error("Error initiating MagickLink on Phone number: {}", e.getMessage());
            throw new RuntimeException("Error initiating MagickLink on Phone number", e);
        }
    }

    public InitiateAuthResponse initiateMagicLinkToEmail(String email, String redirectURI) {
        try {
            MagicLinkPayload payload = MagicLinkPayload.builder()
                    .email(email)
                    .redirectURI(redirectURI)
                    .expiry(DEFAULT_EXPIRATION_TIME) //Optional Default is 5 min.
                    .build();

            InitiateAuthResponse initiateAuthResponse = otpLessAuth.sendMagicLink(payload);

            if (initiateAuthResponse.isSuccess()) {
                log.info("Email Magic Link Initiated successfully. Request Id:  {}", initiateAuthResponse.getRequestId());
            } else {
                log.info("Email Magic Link Failed due to {}", initiateAuthResponse.getErrorMessage());
            }
            return initiateAuthResponse;
        } catch (Exception e) {
            log.error("Error initiating MagickLink on Email: {}", e.getMessage());
            throw new RuntimeException("Error initiating MagickLink on Email", e);
        }
    }

    public InitiateAuthResponse initiateOTPLinkToPhoneNumber(String phoneNumber, String redirectURI) {
        try {
            OTPLinkPayload payload = OTPLinkPayload.builder()
                    .phoneNumber(phoneNumber)
                    .channels(Arrays.asList("WHATSAPP", "SMS", "VIBER"))//Optional
                    .redirectURI(redirectURI)
                    .expiry(DEFAULT_EXPIRATION_TIME) //Optional Default is 5 min.
                    .build();

            InitiateAuthResponse initiateAuthResponse = otpLessAuth.sendOTPLink(payload);

            if (initiateAuthResponse.isSuccess()) {
                log.info("OTP with Magic Link Initiated successfully. Request Id:  {}", initiateAuthResponse.getRequestId());
                //Redirect User to initiateAuthResponse.getLink()
            } else {
                log.info("Failed due to {}", initiateAuthResponse.getErrorMessage());
            }
            return initiateAuthResponse;
        } catch (Exception e) {
            log.error("Error initiating MagickLink and OTP on Phone number: {}", e.getMessage());
            throw new RuntimeException("Error initiating MagickLink and OTP on Phone number", e);
        }
    }

    public InitiateAuthResponse initiateOTPLinkToEmail(String email, String redirectURI) {
        try {

            OTPLinkPayload payload = OTPLinkPayload.builder()
                    .email(email)
                    .redirectURI(redirectURI)
                    .expiry(DEFAULT_EXPIRATION_TIME) //Optional Default is 5 min.
                    .build();
            InitiateAuthResponse initiateAuthResponse = otpLessAuth.sendOTPLink(payload);

            if (initiateAuthResponse.isSuccess()) {
                log.info("Email OTP and Magic Link Initiated successfully. Request Id:  {}", initiateAuthResponse.getRequestId());
            } else {
                log.info("Email OTP and Magic Link Failed due to {}", initiateAuthResponse.getErrorMessage());
            }
            return initiateAuthResponse;
        } catch (Exception e) {
            log.error("Error initiating MagickLink and OTP on Email: {}", e.getMessage());
            throw new RuntimeException("Error initiating MagickLink and OTP on Email", e);
        }
    }

    public VerifyCodeResponse verifyCode(String code) {
        try {

            VerifyCodeResponse verifyCodeResponse = otpLessAuth.verifyCode(code);

            if (verifyCodeResponse.isSuccess()) {
                log.info("Code verification successful. Auth details:  {}", verifyCodeResponse);
            } else {
                log.info("Code verification Failed due to {}", verifyCodeResponse.getErrorMessage());
            }
            return verifyCodeResponse;
        } catch (Exception e) {
            log.error("Error verifying Code: {}", e.getMessage());
            throw new RuntimeException("Error verifying Code", e);
        }
    }

    public AuthDetails status(String requestId) {
        try {
            AuthDetails authDetails = otpLessAuth.status(requestId);

            if (authDetails.isSuccess()) {
                log.info("Auth details fetched successfully for Request Id: {}", requestId);
            } else {
                log.error("Failed to fetch status for Request Id: {} due to {}", requestId, authDetails.getErrorMessage());
            }
            return authDetails;
        } catch (Exception e) {
            log.error("Error fetching status: {}", e.getMessage());
            throw new RuntimeException("Error fetching status", e);
        }
    }
}
