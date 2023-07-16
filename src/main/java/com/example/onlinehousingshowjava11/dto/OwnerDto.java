package com.example.onlinehousingshowjava11.dto;

import com.example.onlinehousingshowjava11.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerDto {
    private int id;
    private String ownerUserName;
    private String ownerName;
    private String ownerEmail;
    private String password;
    private Timestamp createDate;
    private Timestamp updatedDate;
    private String jwtToken;



    public static OwnerDto ownerData(Owner owner, String jwtToken){
        return new OwnerDto(owner.getId(),owner.getOwnerUserName(),owner.getOwnerName(),owner.getOwnerEmail(),owner.getPassword()
                ,owner.getCreateDate(),owner.getUpdatedDate(),jwtToken);
    }
}
