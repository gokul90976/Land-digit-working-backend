package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.LPSVillageEntity;

@Repository
public interface LPSVillageRepo extends JpaRepository<LPSVillageEntity,Long> {
    
     @Query(value ="SELECT * from lps_village WHERE N_UNIQUE_ID= ?1 AND N_FILE_ID=?2",nativeQuery = true)
     List<LPSVillageEntity> getDataUniqueId(int N_UNIQUE_ID , Long N_FILE_ID);

	
}
