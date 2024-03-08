package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bocxy.landDigit.core.landDigitV2.entity.FourOneEntity;

@Repository
public interface FourOneEntityRepo extends JpaRepository<FourOneEntity, Long> {
	

    @Query(value = "SELECT N_ID, N_UNIQUE_ID, V_GAZETTE_REF_NO, V_4_ONE_GO_REF_NO, D_DATE_OF_4_ONE_GO, D_DATE_OF_GAZETTE_NOTIFICATION, D_DATE_OF_LOCALITY,"
    		+ " V_REF_NO, V_TOTAL_EXTENT, V_FILE_1_FILENAME, V_FILE_2_FILENAME, V_FILE_1_FILEPATH, V_FILE_2_FILEPATH FROM  4_one WHERE N_UNIQUE_ID= ?1", nativeQuery = true)
    List<FourOneEntity> findByUniqueId(int N_UNIQUE_ID);

}
