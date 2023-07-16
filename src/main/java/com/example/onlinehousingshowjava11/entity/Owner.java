package com.example.onlinehousingshowjava11.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "owner")
@Data
@Builder

@AllArgsConstructor

public class Owner implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @NotBlank(message = "Username cannot be blank")
    @Column(name = "UserName",nullable = false)
    private String ownerUserName;
    @NotBlank(message = "Name cannot be blank")
    @Column(name = "OwnerName",nullable = false)
    private String ownerName;
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "Email",nullable = false)
    private String ownerEmail;
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "Password",nullable = false)
    private String password;
    @NotBlank(message = "CreatedDate cannot be blank")
    @Column(name = "CreatedDate",nullable = false)
    private Timestamp createDate;
    @NotBlank(message = "UpdatedDate cannot be blank")
    @Column(name = "UpdatedDate",nullable = false)
    private Timestamp updatedDate;
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,orphanRemoval = true)

    private List<House> houses =new ArrayList<>();

    public Owner(String ownerUserName, String ownerName, String ownerEmail, String password, Timestamp createDate, Timestamp updatedDate) {
        this.ownerUserName = ownerUserName;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.password = password;
        this.createDate = createDate;
        this.updatedDate = updatedDate;
    }

    public Owner(){
        createDate=Timestamp.valueOf(LocalDateTime.now());
    }

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return ownerName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}


