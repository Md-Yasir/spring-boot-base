package com.yass.sprinboot.controller;

import com.yass.sprinboot.dto.LoginDto;
import com.yass.sprinboot.dto.LoginResponse;
import com.yass.sprinboot.dto.CreateUserDto;
import com.yass.sprinboot.dto.ResponseDto;
import com.yass.sprinboot.exception.UnAuthorizedException;
import com.yass.sprinboot.service.AuthenticationService;
import com.yass.sprinboot.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("register")
    public ResponseEntity<LoginResponse> register(
            @RequestBody CreateUserDto createUserDto
    ) {
        return ResponseEntity.ok(service.register(createUserDto));
    }

    @PostMapping("auth")
    public ResponseDto<LoginResponse> authenticate(@RequestBody LoginDto request) throws UnAuthorizedException {
        return ResponseUtil.success(service.authenticate(request));
    }

    @PostMapping("rtoken")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
