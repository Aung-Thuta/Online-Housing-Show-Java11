package com.example.onlinehousingshowjava11.model.services;

import com.example.onlinehousingshowjava11.model.dto.OwnerDto;
import com.example.onlinehousingshowjava11.model.dto.form.OwnerForm;
import com.example.onlinehousingshowjava11.model.entity.Owner;
import com.example.onlinehousingshowjava11.model.entity.Role;
import com.example.onlinehousingshowjava11.model.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;

@Service
public class OwnerServices {
    @Autowired
    private OwnerRepo ownerRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtServices jwtServices;
    @Autowired
    private AuthenticationManager authenticationManager;

//    For Creating Owner
    public OwnerDto save(@Valid OwnerForm form){
        Owner owner = new Owner();
        owner.setOwnerUserName(form.getOwnerUserName());
        owner.setOwnerName(form.getOwnerName());
        owner.setOwnerEmail(form.getOwnerEmail());
        owner.setPassword(passwordEncoder.encode(form.getPassword()));
        owner.setCreatedDate(LocalDate.now());
        owner.setUpdatedDate(LocalDate.now());
        owner.setRole(Role.OWNER);
        ownerRepo.save(owner);
        var jwtToken = jwtServices.generateToken(owner);
        return OwnerDto.ownerData(owner,jwtToken);
    }

//    For Logging In
    public OwnerDto authenticate(String ownerUserName,String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        ownerUserName,password
                )
        );
        var user = ownerRepo.findByOwnerUserName(ownerUserName).orElseThrow();
        var jwtToken = jwtServices.generateToken(user);
        return OwnerDto.ownerData(user,jwtToken);

    }
}
























