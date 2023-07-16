package com.example.onlinehousingshowjava11.dto;

import com.example.onlinehousingshowjava11.entity.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseDto {
    private int id;
    private String housingName;
    private String address;
    private long numberOfFloors;
    private long numberOfMasterRoom;
    private long numberOfSingleRoom;
    private long amount;
    private String owner;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public static HouseDto houseData(House house){
        return new HouseDto(house.getId(),house.getHousingName(),house.getAddress(),house.getNumberOfFloor(),
                house.getNumberOfMasterRoom(),house.getNumberOfSingleRoom(),house.getAmount(),
                house.getOwner().getOwnerUserName(),
                house.getCreateDate(),house.getUpdatedDate());
    }

}
