package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.AwardFileEntity;

@Repository
public interface AwardFileRepo extends JpaRepository<AwardFileEntity,Long> {
    
	 @Query(value = "SELECT * FROM award_file WHERE N_UNIQUE_ID= ?1", nativeQuery = true)
	    List<AwardFileEntity> findByUniqueId(int N_UNIQUE_ID);

	@Query(value = "SELECT af.*, " +
			"COALESCE(jr.totalAmountDirectPayment, 0) AS totalAmountDirectPayment, " +
			"COALESCE(jr.totalAmountCourtDeposit, 0) AS totalAmountCourtDeposit, " +
			"COALESCE(jr.totalAmountRevenuePayment, 0) AS totalAmountRevenuePayment " +
			"FROM landdigit_db.award_file af " +
			"LEFT JOIN ( " +
			"    SELECT COALESCE(adp.n_file_id, acdp.n_file_id, arp.n_file_id) AS nId, " +
			"           adp.total_amount AS totalAmountDirectPayment, " +
			"           acdp.total_amount AS totalAmountCourtDeposit, " +
			"           arp.total_amount AS totalAmountRevenuePayment " +
			"    FROM (SELECT n_file_id, SUM(v_amount) AS total_amount FROM award_direct_payment GROUP BY n_file_id) AS adp " +
			"    LEFT JOIN (SELECT n_file_id, SUM(v_amount) AS total_amount FROM award_court_deposit_payment GROUP BY n_file_id) AS acdp ON adp.n_file_id = acdp.n_file_id " +
			"    LEFT JOIN (SELECT n_file_id, SUM(v_amount) AS total_amount FROM award_revenue_payment GROUP BY n_file_id) AS arp ON COALESCE(adp.n_file_id, acdp.n_file_id) = arp.n_file_id " +
			"    UNION ALL " +
			"    SELECT COALESCE(adp.n_file_id, acdp.n_file_id, arp.n_file_id) AS nId, " +
			"           adp.total_amount AS totalAmountDirectPayment, " +
			"           acdp.total_amount AS totalAmountCourtDeposit, " +
			"           arp.total_amount AS totalAmountRevenuePayment " +
			"    FROM (SELECT n_file_id, SUM(v_amount) AS total_amount FROM award_direct_payment GROUP BY n_file_id) AS adp " +
			"    RIGHT JOIN (SELECT n_file_id, SUM(v_amount) AS total_amount FROM award_court_deposit_payment GROUP BY n_file_id) AS acdp ON adp.n_file_id = acdp.n_file_id " +
			"    RIGHT JOIN (SELECT n_file_id, SUM(v_amount) AS total_amount FROM award_revenue_payment GROUP BY n_file_id) AS arp ON COALESCE(adp.n_file_id, acdp.n_file_id) = arp.n_file_id " +
			"    WHERE adp.n_file_id IS NULL OR acdp.n_file_id IS NULL OR arp.n_file_id IS NULL " +
			") AS jr ON af.n_id = jr.nId",
			nativeQuery = true)
	List<Object[]> findAwardFileWithTotals();


}
