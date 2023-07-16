package com.example.onlinehousingshowjava11.model.dto;

import com.example.onlinehousingshowjava11.model.entity.Housing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HousingDto {
    private int id;
    private String housingName;
    private String address;
    private int numberOfFloors;
    private int numberOfMasterRoom;
    private int numberOfSingleRoom;
    private int amount;
    private String owner;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public static HousingDto housingData(Housing housing){
        return new HousingDto(housing.getId(),housing.getHousingName(),housing.getAddress(),housing.getNumberOfFloors(),
                housing.getNumberOfMasterRoom(),housing.getNumberOfSingleRoom(),housing.getAmount(),housing.getOwner().getOwnerUserName(),
                housing.getCreatedDate(),housing.getUpdatedDate());
    }
}





























