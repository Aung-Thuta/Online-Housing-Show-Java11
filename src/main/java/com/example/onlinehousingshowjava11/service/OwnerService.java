package com.example.onlinehousingshowjava11.service;

import com.example.onlinehousingshowjava11.dao.OwnerDao;
import com.example.onlinehousingshowjava11.dto.OwnerDto;
import com.example.onlinehousingshowjava11.entity.Owner;
import com.example.onlinehousingshowjava11.entity.Role;
import com.example.onlinehousingshowjava11.form.OwnerForm;
import com.example.onlinehousingshowjava11.repo.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerDao ownerDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private List<Owner> list(){
        return ownerDao.findAll();
    }


    public OwnerDto save(@Valid OwnerForm ownerForm) {

            Owner owner = new Owner();
            owner.setOwnerUserName(ownerForm.getOwnerUserName());
            owner.setOwnerName(ownerForm.getOwnerName());
            owner.setOwnerEmail(ownerForm.getOwnerEmail());
            owner.setPassword(passwordEncoder.encode(ownerForm.getPassword()));
            owner.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            owner.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
            owner.setRole(Role.OWNER);
            ownerRepository.save(owner);
            var jwtToken = jwtService.generateToken(owner);
            return OwnerDto.ownerData(owner,jwtToken);
        }


    public OwnerDto authenticate(String ownerUserName, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        ownerUserName,password
                )
        );
        var user = ownerRepository.findByOwnerUserName(ownerUserName).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return OwnerDto.ownerData(user,jwtToken);

    }
}



