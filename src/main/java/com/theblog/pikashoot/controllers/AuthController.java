package com.theblog.pikashoot.controllers;


import com.theblog.pikashoot.dto.LoginDTO;
import com.theblog.pikashoot.dto.ResponseDTO;
import com.theblog.pikashoot.dto.UserDTO;

import com.theblog.pikashoot.security.JwtConfig;
import com.theblog.pikashoot.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager manager;
    private final JwtConfig jwtConfig;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager manager, JwtConfig jwtConfig) {
        this.authService = authService;
        this.manager = manager;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        authService.registerUser(userDTO);
        return new ResponseEntity<>("User is registered Successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO){
        try{
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtConfig.generateJwtToken(authentication);

            ResponseDTO dto = new ResponseDTO("Login successful", jwtToken);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseDTO("Login failed. Invalid credentials", null), HttpStatus.UNAUTHORIZED);
        }

    }

}
