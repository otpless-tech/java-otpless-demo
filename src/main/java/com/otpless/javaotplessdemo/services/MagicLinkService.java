package com.otpless.javaotplessdemo.services;

import com.otpless.authsdk.MagicLinkTokens;
import com.otpless.authsdk.OTPLessAuth;
import com.otpless.authsdk.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MagicLinkService {

  public static MagicLinkTokens generateMagicLink(String mobile,String email,String redirectURI,
                                                  String clientId,String clientSecret,
                                                  String channel){
    try {
      MagicLinkTokens magicLinkTokens = OTPLessAuth.generateMagicLink(
          mobile,
          email,
          redirectURI,
          clientId,
          clientSecret,
          channel
      );

      // Access magic link token
      for (MagicLinkTokens.RequestId requestId : magicLinkTokens.getRequestIds()) {
        log.info("Channel/Type: " + requestId.getType());
        log.info("Token/value: " + requestId.getValue());
      }

      return magicLinkTokens;
    } catch (Exception e) {
      // Handle exceptions
    }
      return new MagicLinkTokens();
  }

  public static UserDetail verifyCode(String code,String clientId,String clientSecret){
    try {
      UserDetail userDetail = OTPLessAuth.verifyCode(
          code,
          clientId,
          clientSecret
      );

      // Access user details
      log.info("User Name: " + userDetail.getName());
      log.info("User Email: " + userDetail.getEmail());
      // ... (Access other user details as needed)

    } catch (Exception e) {
      // Handle exceptions
    }
    return new UserDetail();
  }

  public static UserDetail verifyToken(String token,String clientId,String clientSecret){
    try {
      UserDetail userDetail = OTPLessAuth.verifyCode(
          token,
          clientId,
          clientSecret
      );

      // Access user details
      log.info("User Name: " + userDetail.getName());
      log.info("User Email: " + userDetail.getEmail());
      // ... (Access other user details as needed)

    } catch (Exception e) {
      // Handle exceptions
    }
    return new UserDetail();
  }
}
