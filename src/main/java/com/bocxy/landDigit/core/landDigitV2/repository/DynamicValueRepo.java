package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.DynamicValueEntity;

@Repository
public interface DynamicValueRepo extends JpaRepository<DynamicValueEntity,Long>{
	@Query(value = "SELECT * from dynamic_value WHERE N_UNIQUE_ID= ?1 AND N_FILE_ID=?2 AND V_FILE_NAME =?3",nativeQuery = true)
    List<DynamicValueEntity> getByUniqueId(int N_UNIQUE_ID,Long file_id,String file_name);
    
}
