package com.musicreview.api.controllers;

import com.musicreview.api.dto.UserDTO;
import com.musicreview.api.models.Role;
import com.musicreview.api.models.UserEntity;
import com.musicreview.api.repositories.RoleRepository;
import com.musicreview.api.repositories.UserRepository;
import com.musicreview.api.responses.AuthResponse;
import com.musicreview.api.security.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenGenerator jwtTokenGenerator;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTTokenGenerator jwtTokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        if (userRepository.existsByUsername(userDTO.getUsername()))
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        userEntity.setRoles(Collections.singletonList(roles));

        userRepository.save(userEntity);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

}
