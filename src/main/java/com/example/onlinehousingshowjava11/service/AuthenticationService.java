package com.example.onlinehousingshowjava11.service;

import com.example.onlinehousingshowjava11.auth.AuthenticationRequest;
import com.example.onlinehousingshowjava11.auth.AuthenticationResponse;
import com.example.onlinehousingshowjava11.auth.RegisterRequest;
import com.example.onlinehousingshowjava11.entity.Role;
import com.example.onlinehousingshowjava11.entity.Owner;
import com.example.onlinehousingshowjava11.repo.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final OwnerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var owner= Owner.builder()
                .ownerName(request.getName())
                .ownerEmail(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.OWNER)
                .build();
        repository.save(owner);
        var jwtToken = jwtService.generateToken((UserDetails) owner);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByOwnerUserName(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
