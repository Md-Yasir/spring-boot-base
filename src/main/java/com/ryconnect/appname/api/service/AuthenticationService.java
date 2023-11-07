package com.ryconnect.appname.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ryconnect.appname.api.dto.LoginDto;
import com.ryconnect.appname.api.dto.LoginResponse;
import com.ryconnect.appname.api.exception.UnAuthorizedException;
import com.ryconnect.appname.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
//    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger log = LoggerFactory.getLogger("springboot-service");

//    public LoginResponse register(CreateUserDto createUserDto) {
//        var user = User.builder()
//                .firstname(createUserDto.getFirstname())
//                .lastname(createUserDto.getLastname())
//                .email(createUserDto.getEmail())
//                .password(passwordEncoder.encode(createUserDto.getPassword()))
//                .roles(createUserDto.getRoles())
//                .build();
//        var savedUser = repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
////        saveUserToken(savedUser, jwtToken);
//        return LoginResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }

    public LoginResponse authenticate(LoginDto request) throws UnAuthorizedException {
        try {
        	System.out.println("check ------------------ 3");
        	
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserId(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
        	System.out.println("check ------------------ error");

            log.error("UnAuthorized " + ex.getMessage());
            throw new UnAuthorizedException("UnAuthorized");
        }
        
        log.info("############### login");
        
        var user = repository.findById(Integer.parseInt(request.getUserId()))
                .orElseThrow();
        
        log.info("############### user" + user.getFirstname());
        
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
//            revokeAllUserTokens(user);
//            saveUserToken(user, jwtToken);
            return LoginResponse.builder()
                .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                .build();
    }

    /*private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }*/

//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var user = this.repository.findByEmail(userEmail)
//                    .orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
////                revokeAllUserTokens(user);
////                saveUserToken(user, accessToken);
//                var authResponse = LoginResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
}
