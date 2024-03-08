package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.LeftOverLPS4OneEntity;

@Repository
public interface LeftOverLPS4OneRepo extends JpaRepository<LeftOverLPS4OneEntity,Long> {
	

	@Query(value = "SELECT N_ID, N_UNIQUE_ID, V_SURVEY_NO , V_EXTENT FROM  left_over_lps_4_one WHERE N_UNIQUE_ID= ?1", nativeQuery = true)
    List<LeftOverLPS4OneEntity> findByUniqueId(int N_UNIQUE_ID);

	
    
}
