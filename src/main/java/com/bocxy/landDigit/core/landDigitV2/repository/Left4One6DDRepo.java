package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.Left4One6DDEntity;

@Repository
public interface Left4One6DDRepo extends JpaRepository<Left4One6DDEntity,Long> {
	
	@Query(value = "SELECT N_ID, N_UNIQUE_ID, V_SURVEY_NO , V_EXTENT FROM  left_4_one_6dd WHERE N_UNIQUE_ID= ?1", nativeQuery = true)
    List<Left4One6DDEntity> findByUniqueId(int N_UNIQUE_ID);

    
}
