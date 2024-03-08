package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.AwardPossessionTakenOverEntity;

@Repository
public interface AwardPossessionTakenOverRepo  extends JpaRepository<AwardPossessionTakenOverEntity,Long>{
	
	@Query(value = "SELECT * from award_possession_taken_over WHERE N_UNIQUE_ID= ?1 AND N_FILE_ID=?2",nativeQuery = true)
    List<AwardPossessionTakenOverEntity> getByUniqueId(int N_UNIQUE_ID,Long file_id);

    
}
