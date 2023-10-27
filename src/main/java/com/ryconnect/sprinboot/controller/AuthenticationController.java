package com.ryconnect.sprinboot.controller;

import com.ryconnect.sprinboot.dto.CreateUserDto;
import com.ryconnect.sprinboot.dto.LoginDto;
import com.ryconnect.sprinboot.dto.LoginResponse;
import com.ryconnect.sprinboot.dto.ResponseDto;
import com.ryconnect.sprinboot.exception.UnAuthorizedException;
import com.ryconnect.sprinboot.service.AuthenticationService;
import com.ryconnect.sprinboot.utils.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
//@RequestMapping("/v1/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

//    @PostMapping("register")
//    public ResponseEntity<LoginResponse> register(
//            @RequestBody CreateUserDto createUserDto
//    ) {
//        return ResponseEntity.ok(service.register(createUserDto));
//    }

    @PostMapping("/auth")
    public ResponseDto<LoginResponse> authenticate(@RequestBody LoginDto request) throws UnAuthorizedException {
        return ResponseUtil.success(service.authenticate(request));
    }

//    @PostMapping("rtoken")
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        service.refreshToken(request, response);
//    }
}
