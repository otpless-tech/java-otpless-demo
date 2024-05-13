package com.otpless.javaotplessdemo.controller;

import com.otpless.authsdk.MagicLinkTokens;
import com.otpless.authsdk.UserDetail;
import com.otpless.javaotplessdemo.services.MagicLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MagicLinkController {


  @GetMapping(value = "/magic-link/send", produces = MediaType.APPLICATION_JSON_VALUE)
  public MagicLinkTokens getMagicLink(@RequestParam String mobile,@RequestParam String email
      ,@RequestParam String redirectURI, @RequestParam String clientId, @RequestParam String clientSecret, @RequestParam String channel){
    try {
      return MagicLinkService.generateMagicLink(mobile, email, redirectURI, clientId, clientSecret, channel);
    } catch (Exception e) {
      // Handle exceptions
      return new MagicLinkTokens();
    }
  }

  @GetMapping(value = "/verify-code", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDetail verifyCode(@RequestParam String code, @RequestParam String clientId,
                      @RequestParam String clientSecret){
    try {
      return MagicLinkService.verifyCode(code,clientId, clientSecret);
    } catch (Exception e) {
      // Handle exceptions
      return new UserDetail();
    }
  }

  @GetMapping(value = "/verify-token", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDetail verifyToken(@RequestParam String token, @RequestParam String clientId,
                      @RequestParam String clientSecret){
    try {
      return MagicLinkService.verifyToken(token,clientId, clientSecret);
    } catch (Exception e) {
      // Handle exceptions
      return new UserDetail();
    }
  }

}
