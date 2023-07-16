package com.example.onlinehousingshowjava11.repo;

import com.example.onlinehousingshowjava11.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/*import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.List;


@Repository
public interface HouseRepository extends JpaRepository<House,Integer>, JpaSpecificationExecutor<House> {



    @Query("SELECT h FROM House h WHERE h.owner.ownerUserName = :ownerUserName " +
            "AND (:housingName IS NULL OR h.housingName LIKE %:housingName%) " +
            "AND (:floors IS NULL OR h.numberOfFloor = :floors OR (:floors = 0 AND h.numberOfFloor IS NULL)) " +
            "AND (:masterRoom IS NULL OR h.numberOfMasterRoom = :masterRoom) " +
            "AND (:singleRoom IS NULL OR h.numberOfSingleRoom = :singleRoom) " +
            "AND (:amount IS NULL OR h.amount = :amount) " +
            "AND (:createdDate IS NULL OR h.createDate = :createDate)")
    List<House> findAllByOwnerOwnerUserName(String ownerUserName, String housingName, Long floors
            , Long masterRoom, Long singleRoom, Long amount,
                                            Timestamp createdDate, Pageable pageable);

}*/
