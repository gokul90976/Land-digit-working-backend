package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.LandDigitMediaEntity;

@Repository
public interface LandDigitMediaRepo extends JpaRepository<LandDigitMediaEntity,Long> {

    @Query(value = "select * from land_digit_media where N_UNIQUE_ID=?1" , nativeQuery = true)
    List<LandDigitMediaEntity> getAllMediaDetails(Long uniqueId);
    
}
