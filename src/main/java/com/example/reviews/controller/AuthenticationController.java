package com.example.reviews.controller;

import com.example.reviews.model.User;
import com.example.reviews.model.dto.UserRegistrationDto;
import com.example.reviews.model.dto.ReviewUserJwtResponseDto;
import com.example.reviews.security.CustomUserDetailsService;
import com.example.reviews.security.JwtTokenUtil;
import com.example.reviews.service.UserService;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping
public class AuthenticationController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private CustomUserDetailsService userDetailsService;


    public AuthenticationController(UserService userService, AuthenticationManager authManager,
                                    JwtTokenUtil jwtTokenUtil,
                                    CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/registration")
    private User register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        return userService.save(user);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ReviewUserJwtResponseDto> generateAuthenticationToken
            (@RequestBody @Valid UserRegistrationDto userRegistrationDto)
            throws Exception {
        authenticate(userRegistrationDto.getEmail(), userRegistrationDto.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userRegistrationDto.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new ReviewUserJwtResponseDto(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
