package com.example.onlinehousingshowjava11.repo;

import com.example.onlinehousingshowjava11.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    Optional<Owner> findByOwnerUserName(String ownerUserName);
}
