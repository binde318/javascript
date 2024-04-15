package com.binde.AuthenticationSystem.service.impl;

import com.binde.AuthenticationSystem.dto.LoginRequest;
import com.binde.AuthenticationSystem.dto.Request;
import com.binde.AuthenticationSystem.dto.Response;
import com.binde.AuthenticationSystem.dto.UserInfo;
import com.binde.AuthenticationSystem.entity.User;
import com.binde.AuthenticationSystem.repository.UserRepository;
import com.binde.AuthenticationSystem.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<Response> signup(Request request) {
        //if user exists -return error
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Response.builder()
                    .statusCode(400)
                    .responseMessage("attempt to save duplicate user record")
                    .build());
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        User savedUser =userRepository.save(user);
        return ResponseEntity.ok(Response.builder()
                        .statusCode(200)
                        .responseMessage("SUCCESS")
                        .userInfo(modelMapper.map(savedUser, UserInfo.class))
                .build());
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) {

        return null;
    }

//    @Override
//    public Response sendOtp() {
//        return null;
//    }
//
//    @Override
//    public Response validateOtp() {
//        return null;
//    }

    @Override
    public Response resetPassword() {

        return null;
    }

    @Override
    public Response changePassword() {
        return null;
    }
}
