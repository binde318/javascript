package com.binde.AuthenticationSystem.controller;

import com.binde.AuthenticationSystem.dto.OtpRequest;
import com.binde.AuthenticationSystem.dto.OtpValidationRequest;
import com.binde.AuthenticationSystem.dto.Response;
import com.binde.AuthenticationSystem.service.impl.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/otp")
public class OtpController {
    private final OtpService otpService;
    @PostMapping("sendOtp")
    public Response sendOtp(@RequestBody OtpRequest otpRequest){
        return otpService.sendOtp(otpRequest);
    }
    @PostMapping("validateOtp")
    public Response validateOtp(@RequestBody OtpValidationRequest otpValidationRequest){
      return otpService.validateOtp(otpValidationRequest);
    }
}
