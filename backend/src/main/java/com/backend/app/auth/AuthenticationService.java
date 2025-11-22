package com.backend.app.auth;

import java.util.List;
import java.util.Objects;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.app.auth.dto.AuthenticationRequest;
import com.backend.app.auth.dto.AuthenticationResponse;
import com.backend.app.auth.dto.RegisterRequest;
import com.backend.app.config.security.JwtService;
import com.backend.app.role.Role;
import com.backend.app.role.RoleRepository;
import com.backend.app.user.User;
import com.backend.app.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

       private final UserRepository repository;
       private final RoleRepository roleRepository;
       private final PasswordEncoder passwordEncoder;
       private final JwtService jwtService;
       private final AuthenticationManager authenticationManager;

       public AuthenticationResponse register(RegisterRequest request) {
              Role userRole = Objects.requireNonNull(roleRepository.findByName("user")
                            .orElseGet(() -> roleRepository.save(Role.builder().name("user").build())));

              User user = User.builder()
                            .username(request.getUsername())
                            .email(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .roles(List.of(userRole))
                            .build();
              repository.save(user);
              var jwtToken = jwtService.generateToken(user);
              return AuthenticationResponse.builder()
                            .token(jwtToken)
                            .build();
       }

       public AuthenticationResponse authenticate(AuthenticationRequest request) {
              authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                          request.getUsername(),
                                          request.getPassword()));
              var user = repository.findByUsername(request.getUsername())
                            .orElseThrow();
              var jwtToken = jwtService.generateToken(user);
              return AuthenticationResponse.builder()
                            .token(jwtToken)
                            .build();
       }
}
