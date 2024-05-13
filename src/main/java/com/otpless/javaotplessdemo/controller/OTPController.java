package com.otpless.javaotplessdemo.controller;

import com.otpless.authsdk.OTPResponse;
import com.otpless.authsdk.OTPVerificationResponse;
import com.otpless.javaotplessdemo.services.OTPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OTPController {


  @GetMapping(value = "/send/otp", produces = MediaType.APPLICATION_JSON_VALUE)
  public OTPResponse sendOTP(@RequestParam String orderId,@RequestParam String phoneNumber
      ,@RequestParam String email, @RequestParam String hash, @RequestParam Integer expiredInSec,
                                 @RequestParam Integer otpLength, @RequestParam String channel){
    try {
      return OTPService.sendOTP(orderId, phoneNumber, email, hash, expiredInSec, otpLength, channel);
    } catch (Exception e) {
      // Handle exceptions
      return new OTPResponse();
    }
  }

  @GetMapping(value = "/verify/otp", produces = MediaType.APPLICATION_JSON_VALUE)
  public OTPVerificationResponse verifyOTP(@RequestParam String orderId, @RequestParam String otp,
                                           @RequestParam String phoneNumber, @RequestParam String email){
    try {
      return OTPService.verifyOTP(orderId, otp, phoneNumber, email);
    } catch (Exception e) {
      // Handle exceptions
      return new OTPVerificationResponse();
    }
  }

  @GetMapping(value = "/resend/otp", produces = MediaType.APPLICATION_JSON_VALUE)
  public OTPResponse resendOTP(@RequestParam String orderId){
    try {
      return OTPService.resendOTP(orderId);
    } catch (Exception e) {
      // Handle exceptions
      return new OTPResponse();
    }
  }

}
