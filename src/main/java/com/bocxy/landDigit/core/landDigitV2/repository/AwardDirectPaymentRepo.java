package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.AwardDirectPaymentEntity;

@Repository
public interface AwardDirectPaymentRepo extends JpaRepository<AwardDirectPaymentEntity,Long> {
	
	@Query(value = "SELECT * from award_direct_payment WHERE N_UNIQUE_ID= ?1 AND N_FILE_ID=?2",nativeQuery = true)
    List<AwardDirectPaymentEntity> getByUniqueId(int N_UNIQUE_ID,Long file_id);

    
}
