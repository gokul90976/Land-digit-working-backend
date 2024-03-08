package com.bocxy.landDigit.core.landDigitV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.LandDigitDataEntity;

@Repository
public interface LandDigitv2MainRepo extends JpaRepository<LandDigitDataEntity,Long> {



    
}
