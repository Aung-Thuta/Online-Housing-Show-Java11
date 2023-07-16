package com.example.onlinehousingshowjava11;

import com.example.onlinehousingshowjava11.dao.OwnerDao;
import com.example.onlinehousingshowjava11.dao.PublicDao;
import com.example.onlinehousingshowjava11.entity.House;
import com.example.onlinehousingshowjava11.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class OnlineHousingShowJava11Application {

	public static void main(String[] args) {
		SpringApplication.run(OnlineHousingShowJava11Application.class, args);
	}

}
