package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bocxy.landDigit.core.landDigitV2.entity.SixDdDynamicFileEntity;

public interface SixDdDynamicFileRepo extends JpaRepository<SixDdDynamicFileEntity,Long> {
	
	 @Query(value = "SELECT * from 6dd_dynamic_file WHERE N_UNIQUE_ID= ?1 AND N_FILE_ID=?2",nativeQuery = true)
	    List<SixDdDynamicFileEntity> getByUniqueId(int N_UNIQUE_ID,Long file_id);
    
}
