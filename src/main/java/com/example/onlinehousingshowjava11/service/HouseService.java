/*package com.example.onlinehousingshowjava11.service;

import com.example.onlinehousingshowjava11.dao.PublicDao;
import com.example.onlinehousingshowjava11.dto.HouseDto;
import com.example.onlinehousingshowjava11.entity.House;
import com.example.onlinehousingshowjava11.entity.Owner;
import com.example.onlinehousingshowjava11.form.HouseForm;
import com.example.onlinehousingshowjava11.repo.HouseRepository;
import com.example.onlinehousingshowjava11.repo.OwnerRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.awt.print.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HouseService {
    @Autowired
    private PublicDao publicDao;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private HouseRepository houseRepository;




    public HouseDto save(@Valid HouseForm houseForm, HttpHeaders headers, String loginToken) {
        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer", "");
        String ownerUserName = Jwts.parser().setSigningKey(loginToken).parseClaimsJws(jwt).getBody().getSubject();


        House house = new House();
        house.setHousingName(houseForm.getHousingName());
        house.setAddress(houseForm.getAddress());
        house.setNumberOfFloor(houseForm.getNumberOfFloors());
        house.setNumberOfMasterRoom(houseForm.getNumberOfMasterRoom());
        house.setNumberOfSingleRoom(houseForm.getNumberOfSingleRoom());
        house.setAmount(houseForm.getAmount());
        Owner owner = ownerRepository.findByOwnerUserName(ownerUserName).orElseThrow(EntityNotFoundException::new);
        house.setOwner(owner);
        house.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        house.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
        return HouseDto.houseData(house);

    }
    @Transactional
    public HouseDto update(int id, HouseForm form, HttpHeaders headers, String loginToken) {

        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer","");
        String ownerUserName = Jwts.parser().setSigningKey(loginToken).parseClaimsJws(jwt).getBody().getSubject();

        return HouseDto.houseData(houseRepository.findById(id).map(ele->{
            ele.setHousingName(form.getHousingName());
            ele.setAddress(form.getAddress());
            ele.setNumberOfFloor(form.getNumberOfFloors());
            ele.setNumberOfMasterRoom(form.getNumberOfMasterRoom());
            ele.setNumberOfSingleRoom(form.getNumberOfSingleRoom());
            ele.setAmount(form.getAmount());
            Owner owner = ownerRepository.findByOwnerUserName(ownerUserName).orElseThrow(EntityNotFoundException::new);
            ele.setOwner(owner);
            ele.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
            return ele;
        }).orElseThrow(EntityNotFoundException::new));
    }


    public List<HouseDto> findByOwnerUserName(HttpHeaders headers, String loginToken,
                                                Optional<String> housingName,Optional<Long> floors,
                                                Optional<Long> masterRoom,Optional<Long> singleRoom,
                                                Optional<Long> amount, Optional<Timestamp> createDate,
                                                int current,int size){
        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer","");
        String ownerUserName = Jwts.parser().setSigningKey(loginToken).parseClaimsJws(jwt).getBody().getSubject();

        Specification<House> spec = withHousingName(housingName).and(withFloors(floors)).and(withMasterRoom(masterRoom))
                .and(withSingleRoom(singleRoom)).and(withAmount(amount)).and(withCreateDate(createDate));

        return houseRepository.findAllByOwnerOwnerUserName(ownerUserName,housingName.orElse(null),floors.orElse(null)
                        ,masterRoom.orElse(null),singleRoom.orElse(null),amount.orElse(null),createDate.orElse(null), (Pageable) PageRequest.of(current,size))
                .stream().map(ele -> HouseDto.houseData(ele)).collect(Collectors.toList());
    }

    //    Finding All Housing Data (Public Api)
    public List<HouseDto> findAllHousingData(Optional<String> housingName, Optional<Long> floors,
                                             Optional<Long> masterRoom, Optional<Long> singleRoom,
                                             Optional<Long> amount, Optional<Timestamp> createDate,
                                             int current, int size){
        var specification = withHousingName(housingName).and(withFloors(floors)).and(withMasterRoom(masterRoom))
                .and(withSingleRoom(singleRoom)).and(withAmount(amount)).and(withCreateDate(createDate));
        return houseRepository.findAll(specification, PageRequest.of(current,size)).stream()
                .map(ele -> HouseDto.houseData(ele)).collect(Collectors.toList());
    }

    private Specification<House> withHousingNameTest(Optional<String> housingName,String ownerUsername) {
        if(housingName.filter(StringUtils::hasLength).isPresent()) {
            Specification data = (root, query, cb) -> cb.like(cb.lower(root.get("housingName")), housingName.get().toLowerCase().concat("%"));
            if (data.equals(ownerUsername)){
                return data;
            }else{
                return null;
            }
        }

        return Specification.where(null);
    }

    private Specification<House> withHousingName(Optional<String> housingName) {
        if(housingName.filter(StringUtils::hasLength).isPresent()) {
            return (root, query, cb) -> cb.like(cb.lower(root.get("housingName")), housingName.get().toLowerCase().concat("%"));
        }

        return Specification.where(null);
    }

    private Specification<House> withFloors(Optional<Long> floors){
        if (floors.filter(a-> a>0).isPresent()){
            return (root,query,cb) -> cb.equal(root.get("numberOfFloors"), floors.get());
        }
        return Specification.where(null);
    }

    private Specification<House> withMasterRoom(Optional<Long> masterRoom){
        if (masterRoom.filter(a-> a>0).isPresent()){
            return (root,query,cb) -> cb.equal(root.get("numberOfMasterRoom"), masterRoom.get());
        }
        return Specification.where(null);
    }

    private Specification<House> withSingleRoom(Optional<Long> singleRoom){
        if (singleRoom.filter(a-> a>0).isPresent()){
            return (root,query,cb) -> cb.equal(root.get("numberOfSingleRoom"), singleRoom.get());
        }
        return Specification.where(null);
    }

    private Specification<House> withAmount(Optional<Long> amount){
        if (amount.filter(a-> a>0).isPresent()){
            return (root,query,cb) -> cb.equal(root.get("amount"), amount.get());
        }
        return Specification.where(null);
    }

    public Specification<House> withCreateDate(Optional<Timestamp> createdDate) {
        if(createdDate.isPresent()) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdDate"), createdDate.get());
        }

        return Specification.where(null);
    }

}*/

