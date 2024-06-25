package com.otpless.javaotplessdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/health-check")
public class BaseController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String healthCheck() {
        return "OTPLess Java SDK Demo.";
    }

}
