package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.LPSFileEntity;

@Repository
public interface LPSFileRepo extends JpaRepository<LPSFileEntity, Long> {

    @Query(value = "SELECT N_UNIQUE_ID,V_FILE_NAME,V_FILE_PATH,V_REF_NO,V_TOTAL_EXTENT,N_ID FROM lps_file WHERE N_UNIQUE_ID= ?1", nativeQuery = true)
    List<LPSFileEntity> findByUniqueId(int N_UNIQUE_ID);

}