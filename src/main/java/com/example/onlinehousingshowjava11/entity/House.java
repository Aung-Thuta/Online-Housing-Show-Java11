package com.example.onlinehousingshowjava11.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "houses")
@Data
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "House name cannot be blank")
    @Column(name = "HouseName",nullable = false)
    private String housingName;
    @NotBlank(message = "Address cannot be blank")
    @Column(name = "Address", nullable = false)
    private String address;
    @NotBlank(message = "Number of floor cannot be blank")
    @Column(name = "Floor",nullable = false)
    private long numberOfFloor;
    @NotBlank(message = "Number of master room cannot be blank")
    @Column(name = "MasterRoom",nullable = false)
    private long numberOfMasterRoom;
    @NotBlank(message = "Number of single room cannot be blank")
    @Column(name = "SingleRoom")
    private long numberOfSingleRoom;
    @NotBlank(message = "Amount cannot be blank")
    @Column(name = "Amount", nullable = false)
    private long amount;

    @NotBlank(message = "Date created cannot be blank")
    @Column(name = "DateCreated",nullable = false)
    private Timestamp createDate;
    @NotBlank(message = "Updated date cannot be blank")
    @Column(name = "UpdatedDated",nullable = false)
    private Timestamp updatedDate;
    @ManyToOne
    private Owner owner;


    public House(String housingName, String address, long numberOfFloor, long numberOfMasterRoom, long numberOfSingleRoom, long amount, Timestamp createDate, Timestamp updatedDate) {
        this.housingName = housingName;
        this.address = address;
        this.numberOfFloor = numberOfFloor;
        this.numberOfMasterRoom = numberOfMasterRoom;
        this.numberOfSingleRoom = numberOfSingleRoom;
        this.amount = amount;
        this.createDate = createDate;
        this.updatedDate = updatedDate;
    }

  public House(){
        createDate= Timestamp.valueOf(LocalDateTime.now());
  }
}
