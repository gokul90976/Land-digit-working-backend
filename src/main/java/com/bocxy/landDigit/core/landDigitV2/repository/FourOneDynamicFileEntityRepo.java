package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bocxy.landDigit.core.landDigitV2.entity.FourOneDynamicFileEntity;
@Repository
public interface FourOneDynamicFileEntityRepo extends JpaRepository<FourOneDynamicFileEntity,Long>{
	
	 @Query(value = "SELECT * from 4_one_dynamic_file WHERE N_UNIQUE_ID= ?1 AND N_FILE_ID=?2",nativeQuery = true)
	    List<FourOneDynamicFileEntity> getByUniqueId(int N_UNIQUE_ID,Long file_id);
    
}
