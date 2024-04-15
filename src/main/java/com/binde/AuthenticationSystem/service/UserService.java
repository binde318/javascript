package com.binde.AuthenticationSystem.service;

import com.binde.AuthenticationSystem.dto.LoginRequest;
import com.binde.AuthenticationSystem.dto.Request;
import com.binde.AuthenticationSystem.dto.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response> signup(Request request);
    ResponseEntity<Response> login(LoginRequest request);
//    Response sendOtp();
//    Response validateOtp();
    Response resetPassword();
    Response changePassword();


}
