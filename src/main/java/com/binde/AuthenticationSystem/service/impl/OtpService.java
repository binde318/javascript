package com.binde.AuthenticationSystem.service.impl;

import com.binde.AuthenticationSystem.dto.*;
import com.binde.AuthenticationSystem.entity.Otp;
import com.binde.AuthenticationSystem.repository.OtpRepository;
import com.binde.AuthenticationSystem.service.UserService;
import com.binde.AuthenticationSystem.utils.AppUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class OtpService {
    private final OtpRepository otpRepository;
    private final EmailService emailService;

    public Response sendOtp(OtpRequest otpRequest){
        //generate the Otp
        //send the otp
        //save the otp
        //generate the Otp
        Otp existingOtp = otpRepository.findByEmail(otpRequest.getEmail());
        if (existingOtp != null){
            otpRepository.delete(existingOtp);
        }
        String  otp = AppUtil.generateOtp();
        log.info("otp: {}", otp);
        //save the otp
        otpRepository.save(Otp.builder()
                        .email(otpRequest.getEmail())
                        .otp(otp)
                        .expiresAt(LocalDateTime.now().plusMinutes(2))
                .build());

        //send the otp
        emailService.sendEmail(EmailDetails.builder()
                        .subject("DO NOT DISCLOSE")
                        .recipient(otpRequest.getEmail())
                        .messageBody("This organization has send you an OTP :" + otp)
                .build());

return Response.builder()
        .statusCode(200)
        .responseMessage("SUCCESS")
        .build();
    }
//ascertain the user sent an otp and also that the otp has not expired
    //ascertain that the otp is correct
    public Response validateOtp(OtpValidationRequest otpValidationRequest){
        Otp otp = otpRepository.findByEmail(otpValidationRequest.getEmail());
        log.info("Email:{}", otpValidationRequest.getEmail());
if (otp == null){
    return Response.builder()
            .statusCode(400)
            .responseMessage("You have not sent an otp")
            .build();
        }
if (otp.getExpiresAt().isBefore(LocalDateTime.now())){
    return Response.builder()
            .statusCode(400)
            .responseMessage("Expired Otp")

            .build();
    }
if (!otp.getOtp().equals(otpValidationRequest.getOtp())){
    return Response.builder()
            .statusCode(400)
            .responseMessage("otp is not valid")
            .build();

}
return Response.builder()
        .statusCode(200)
        .responseMessage("SUCCESS")
        .otpResponse(OtpResponse.builder()
                .isOtpValid(true)
                .build())
        .build();
    }

}
