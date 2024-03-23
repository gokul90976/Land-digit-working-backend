package com.bocxy.landDigit.core.landDigitV2.repository;

import com.bocxy.landDigit.core.landDigitV2.entity.AwardRevenuePaymentEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.AwardUtilisedLhoSelectedExtentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AwardUtilisedLhoSelectedExtentListRepo extends JpaRepository<AwardUtilisedLhoSelectedExtentList, Long> {

    @Query(value = "SELECT * from award_utilised_lho_selected_extent_list WHERE N_UNIQUE_ID= ?1 AND N_FILE_ID=?2",nativeQuery = true)
    List<AwardUtilisedLhoSelectedExtentList> getByUniqueId(int N_UNIQUE_ID, Long file_id);

}