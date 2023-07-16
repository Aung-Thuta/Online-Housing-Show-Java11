package com.example.onlinehousingshowjava11.dao;

import com.example.onlinehousingshowjava11.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerDao extends JpaRepository<Owner,Integer> {
}
