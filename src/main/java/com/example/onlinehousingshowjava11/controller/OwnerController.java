package com.example.onlinehousingshowjava11.controller;

import com.example.onlinehousingshowjava11.dto.OwnerDto;
import com.example.onlinehousingshowjava11.entity.House;
import com.example.onlinehousingshowjava11.form.OwnerForm;
import com.example.onlinehousingshowjava11.service.OwnerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private OwnerService ownerService;

    @PostMapping(path = "/register",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public OwnerDto register(@RequestParam("ownerUserName") String ownerUserName,
                             @RequestParam("ownerName") String ownerName,
                             @RequestParam("ownerEmail") String ownerEmail,
                             @RequestParam("password") String password) {
        OwnerForm ownerform = new OwnerForm();
        ownerform.setOwnerUserName(ownerUserName);
        ownerform.setOwnerName(ownerName);
        ownerform.setOwnerEmail(ownerEmail);
        ownerform.setPassword(password);
        return ownerService.save(ownerform);
    }
    @PostMapping(path = "/authenticate",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public OwnerDto authenticate(@RequestParam("ownerUserName")String ownerUserName,
                                 @RequestParam("password")String password){
        return ownerService.authenticate(ownerUserName,password);
    }

}
