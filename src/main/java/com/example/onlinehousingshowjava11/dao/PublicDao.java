package com.example.onlinehousingshowjava11.dao;

import com.example.onlinehousingshowjava11.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicDao extends JpaRepository<House,Integer> {
}
