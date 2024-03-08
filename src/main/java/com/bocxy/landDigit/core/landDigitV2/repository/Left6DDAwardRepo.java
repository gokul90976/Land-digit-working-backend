package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.Left6DDAwardEntity;

@Repository
public interface Left6DDAwardRepo extends JpaRepository<Left6DDAwardEntity,Long> {
	@Query(value = "SELECT N_ID, N_UNIQUE_ID, V_SURVEY_NO , V_EXTENT FROM  left_6dd_award WHERE N_UNIQUE_ID= ?1", nativeQuery = true)
    List<Left6DDAwardEntity> findByUniqueId(int N_UNIQUE_ID);

}
