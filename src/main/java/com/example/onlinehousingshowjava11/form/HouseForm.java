package com.example.onlinehousingshowjava11.form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseForm {
    private String housingName;
    private String address;
    private long numberOfFloors;
    private long numberOfMasterRoom;
    private long numberOfSingleRoom;
    private long amount;
    private String  owner;
}
