package com.otpless.javaotplessdemo.services;

import com.otpless.authsdk.OTPAuth;
import com.otpless.authsdk.OTPResponse;
import com.otpless.authsdk.OTPVerificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OTPService {


  @Value("${otpless.client.id}")
  private static String clientId;

  @Value("${otpless.client.secret}")
  private static String clientSecret;

  public static OTPResponse sendOTP(String orderId, String phoneNumber, String email, String hash, Integer expiredInSec, Integer otpLength, String channel){
    try {

      OTPAuth otpAuth = new OTPAuth(clientId, clientSecret);
      OTPResponse otpResponse = otpAuth.sendOTP(orderId, phoneNumber, email, hash, expiredInSec, otpLength, channel);

      if (otpResponse.isSuccess()) {
        log.info("OTP sent. orderId=> " + otpResponse.getOrderId());
      } else {
       log.info("OTP send to failed due to " + otpResponse.getErrorMessage());
      }
    } catch (Exception e) {
      // Handle exceptions
    }
      return new OTPResponse();
  }

  public static OTPResponse resendOTP(String orderId){
    try {

      OTPAuth otpAuth = new OTPAuth(clientId, clientSecret);
      OTPResponse otpResponse = otpAuth.resendOTP(orderId);

      if (otpResponse.isSuccess()) {
        log.info("OTP resent. orderId=> " + otpResponse.getOrderId());
      } else {
        log.info("OTP not resend due to " + otpResponse.getErrorMessage());
      }
      return otpResponse;
    } catch (Exception e) {
      // Handle exceptions
    }
    return new OTPResponse();
  }

  public static OTPVerificationResponse verifyOTP(String orderId, String otp, String phoneNumber, String email){
    try {

      OTPAuth otpAuth = new OTPAuth(clientId, clientSecret);
      OTPVerificationResponse otpVerificationResponse = otpAuth.verifyOTP(orderId, otp, phoneNumber, email);

      if (otpVerificationResponse.isSuccess()) {
        log.info("OTP verify API SUCCESS");
        if (Boolean.TRUE.equals(otpVerificationResponse.getIsOTPVerified())) {
          log.info("OTP verified");
        } else {
          log.info("OTP verification failed. due to " + otpVerificationResponse.getReason());
        }
      } else {
        log.info("OTP verification failed in API. due to " + otpVerificationResponse.getErrorMessage());
      }
      return otpVerificationResponse;
    } catch (Exception e) {
      // Handle exceptions
    }
    return new OTPVerificationResponse();
  }
}
