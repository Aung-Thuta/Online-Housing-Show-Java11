/*package com.example.onlinehousingshowjava11.controller;

import com.example.onlinehousingshowjava11.dto.HouseDto;
import com.example.onlinehousingshowjava11.form.HouseForm;
import com.example.onlinehousingshowjava11.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("house")
public class PublicController {

    @Autowired
    private HouseService houseService;


    @Value("${app.secretKey}")
    private String SECRET_KEY;


    @PostMapping("/save-house")
    public HouseDto create(@RequestBody @Validated HouseForm form, BindingResult result,@RequestHeader HttpHeaders headers){
        if (result.hasErrors()){
            throw  new EntityNotFoundException();
        }
        return houseService.save(form,headers,SECRET_KEY);
    }
    @GetMapping("{id}")
    public HouseDto update(@PathVariable int id, @RequestBody @Validated HouseForm form, BindingResult result,@RequestHeader HttpHeaders headers){
        if (result.hasErrors()){
            throw new EntityNotFoundException();
        }
        return houseService.update(id,form,headers,SECRET_KEY);
    }

    @GetMapping("/owner-name")
    public List<HouseDto> findByOwnerUserName(@RequestHeader HttpHeaders httpHeaders,
                                              @RequestParam Optional<String> housingName,
                                              @RequestParam("numberOfFloors") Optional<Long> floors,
                                              @RequestParam Optional<Long> masterRoom,
                                              @RequestParam Optional<Long> singleRoom,
                                              @RequestParam Optional<Long> amount,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<Timestamp> createdDate,
                                              @RequestParam(required = false,defaultValue = "0")int current,
                                              @RequestParam(required = false,defaultValue = "6")int size){
        return houseService.findByOwnerUserName(httpHeaders,SECRET_KEY,housingName,floors,masterRoom,singleRoom,amount,createdDate,current,size);
    }

    @GetMapping("/search")
    public List<HouseDto> findAll(@RequestParam Optional<String> housingName,
                                    @RequestParam("numberOfFloors") Optional<Long> floors,
                                    @RequestParam Optional<Long> masterRoom,
                                    @RequestParam Optional<Long> singleRoom,
                                    @RequestParam Optional<Long> amount,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<Timestamp> createdDate,
                                    @RequestParam(required = false,defaultValue = "0")int current,
                                    @RequestParam(required = false,defaultValue = "6")int size){
        return houseService.findAllHousingData(housingName,floors,masterRoom,singleRoom,amount,createdDate,current,size);
    }



}*/
