package com.bocxy.landDigit.core.landDigitV2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.LandDigitDataDivisionWiseEntity;

@Repository
public interface LandDigitDataDivisionWiseRepo extends JpaRepository<LandDigitDataDivisionWiseEntity,Long> {



    @Query(value = "SELECT\n" +
            "           award.N_UNIQUE_ID,\n" +
            "           land.V_NAME_OF_CIRCLE,\n" +
            "           land.V_NAME_OF_DIVISION,\n" +
            "           land.V_NAME_OF_DISTRICT,\n" +
            "           land.V_NAME_OF_SCHEME,\n" +
            "           land.V_NAME_OF_VILLAGE,\n" + // Added village name
            "           SUM(award.V_TOTAL_EXTENT) AS V_TOTAL_EXTENT,\n" +
            "           SUM(award.V_PHO_TOTAL_EXTENT) AS V_PHO_TOTAL_EXTENT,\n" +
            "           SUM(award.V_PNHO_TOTAL_EXTENT) AS V_PNHO_TOTAL_EXTENT,\n" +
            "           SUM(award.V_PHO_SCHEME_TOTAL_EXTENT) AS V_PHO_SCHEME_TOTAL_EXTENT,\n" +
            "           SUM(award.v_future_dev_extent) AS sum_v_future_dev_extent,\n" +
            "           SUM(award.v_lho_extent_1) AS sum_v_lho_extent_1,\n" +
            "           SUM(award.v_utilised_extent) AS sum_v_utilised_extent,\n" +
            "           SUM(award.v_not_utilised_lho_extent_list) AS sum_v_not_utilised_lho_extent_list,\n" +
            "           (SELECT SUM(V_TOTAL_EXTENT) FROM landdigit_db.6dd_file WHERE N_UNIQUE_ID = award.N_UNIQUE_ID) AS total_extent_6dd,\n" +
            "           (SELECT SUM(V_TOTAL_EXTENT) FROM landdigit_db.4_one WHERE N_UNIQUE_ID = award.N_UNIQUE_ID) AS total_extent_4_one,\n" +
            "           (SELECT SUM(V_AMOUNT) FROM landdigit_db.award_court_deposit_payment WHERE N_UNIQUE_ID = award.N_UNIQUE_ID) AS courtcase,\n"+
            "           (SELECT SUM(V_AMOUNT) FROM landdigit_db.award_direct_payment WHERE N_UNIQUE_ID = award.N_UNIQUE_ID) AS direct,\n"+
            "           (SELECT SUM(V_AMOUNT) FROM landdigit_db.award_revenue_payment WHERE N_UNIQUE_ID = award.N_UNIQUE_ID) AS revenue,\n"+
            "           SUM(award.N_TOTAL_AWARD_AMOUNT) AS N_TOTAL_AWARD_AMOUNT\n," +
            "           SUM(award.v_lnho_extent_1) AS sum_v_lnho_extent_1\n" +
            "           \n" +
            "           FROM\n" +
            "           award_file AS award\n" +
            "           JOIN\n" +
            "           land_digit_data AS land ON award.N_UNIQUE_ID = land.N_UNIQUE_ID\n" +
            "          \n" +
            "           GROUP BY\n" +
            "           award.N_UNIQUE_ID,\n" +
            "           land.V_NAME_OF_CIRCLE,\n" +
            "           land.V_NAME_OF_DISTRICT,\n" +
            "           land.V_NAME_OF_DIVISION,\n" +
            "           land.V_NAME_OF_SCHEME,\n" +
            "           land.V_NAME_OF_VILLAGE;", nativeQuery = true)
    List<Object[]> mainview();





    @Query(value = "SELECT V_NAME_OF_DIVISION FROM land_digit_data_division_wise WHERE V_CITY_OR_RURAL = :values", nativeQuery = true)
    List<String> getDivisionsByCityOrRural(@Param("values") String values);

    @Query(value = "SELECT DISTINCT V_NAME_OF_DISTRICT FROM land_digit_data_division_wise WHERE V_CITY_OR_RURAL = :values", nativeQuery = true)
    List<String> getDistrictsByCityOrRural(@Param("values") String values);

    @Query(value = "SELECT\n" +
            "    ld.V_NAME_OF_DIVISION AS Division,\n" +
            "    ld.V_NAME_OF_CIRCLE AS Circle,\n" +
            "    ld.V_NAME_OF_DISTRICT AS District,\n" + // Include District
            "    ldf.V_NAME_OF_VILLAGE AS Village,\n" +
            "    COUNT(DISTINCT ldf.N_FILE_ID) AS lpscount,\n" +
            "    COUNT(DISTINCT fdf.N_FILE_ID) AS fouronecount,\n" +
            "    COUNT(DISTINCT ddf.N_FILE_ID) AS sixddcount,\n" +
            "    COUNT(DISTINCT adf.N_FILE_ID) AS awardcount, \n" +
            "    ldf.N_UNIQUE_ID as uniqueId \n" +
            "FROM\n" +
            "    land_digit_data ld\n" +
            "    \n" +
            "LEFT JOIN lps_village ldf ON ld.N_UNIQUE_ID = ldf.N_UNIQUE_ID AND ldf.V_NAME_OF_VILLAGE IS NOT NULL\n" +
            "\n" +
            "LEFT JOIN (\n" +
            "    SELECT DISTINCT N_UNIQUE_ID, v_village, N_FILE_ID\n" +
            "    FROM 4_one_dynamic_file\n" +
            "    WHERE v_village IS NOT NULL\n" +
            ") fdf ON ld.N_UNIQUE_ID = fdf.N_UNIQUE_ID AND ldf.V_NAME_OF_VILLAGE = fdf.v_village\n" +
            "\n" +
            "LEFT JOIN (\n" +
            "    SELECT DISTINCT N_UNIQUE_ID, v_village, N_FILE_ID\n" +
            "    FROM 6dd_dynamic_file\n" +
            "    WHERE v_village IS NOT NULL\n" +
            ") ddf ON ld.N_UNIQUE_ID = ddf.N_UNIQUE_ID AND ldf.V_NAME_OF_VILLAGE = ddf.v_village\n" +
            "\n" +
            "LEFT JOIN (\n" +
            "    SELECT DISTINCT N_UNIQUE_ID, v_village, N_FILE_ID\n" +
            "    FROM award_possession_taken_over\n" +
            "    WHERE v_village IS NOT NULL\n" +
            ") adf ON ld.N_UNIQUE_ID = adf.N_UNIQUE_ID AND ldf.V_NAME_OF_VILLAGE = adf.v_village\n" +
            "\n" +
            "GROUP BY\n" +
            "    ld.V_NAME_OF_DIVISION,\n" +
            "    ld.V_NAME_OF_CIRCLE,\n" +
            "    ld.V_NAME_OF_DISTRICT,\n" +
            "    ldf.V_NAME_OF_VILLAGE;\n", nativeQuery = true)
    List<Object[]> maincountvillageView();


    @Query(value = "SELECT\n" +
            "    COALESCE(ld.N_UNIQUE_ID, lps.N_UNIQUE_ID, one.N_UNIQUE_ID, award.N_UNIQUE_ID) AS Unique_id,\n" +
            "    ld.V_NAME_OF_DIVISION AS Division,\n" +
            "    ld.V_NAME_OF_CIRCLE AS Circle,\n" +
            "    ld.V_NAME_OF_SCHEME AS Scheme_Name,\n" +
            "    ld.V_NAME_OF_DISTRICT AS District,\n" +
            "    lps.V_NAME_OF_VILLAGE AS Lps_village,\n" +
            "    one.v_village AS `4one_village`,\n" +
            "    sixdd.v_village AS `6dd_village`,\n" +
            "    award.v_village AS `award_village`\n" +
            "FROM\n" +
            "    (SELECT DISTINCT N_UNIQUE_ID, V_NAME_OF_DIVISION, V_NAME_OF_SCHEME, V_NAME_OF_CIRCLE, V_NAME_OF_DISTRICT FROM land_digit_data) ld\n" +
            "LEFT JOIN\n" +
            "    (SELECT DISTINCT N_UNIQUE_ID, V_NAME_OF_VILLAGE FROM lps_village) lps\n" +
            "    ON ld.N_UNIQUE_ID = lps.N_UNIQUE_ID\n" +
            "LEFT JOIN\n" +
            "    (SELECT DISTINCT N_UNIQUE_ID, v_village FROM 4_one_dynamic_file) one\n" +
            "    ON ld.N_UNIQUE_ID = one.N_UNIQUE_ID AND lps.V_NAME_OF_VILLAGE = one.v_village\n" +
            "LEFT JOIN\n" +
            "    (SELECT DISTINCT N_UNIQUE_ID, v_village FROM 6dd_dynamic_file) sixdd\n" +
            "    ON ld.N_UNIQUE_ID = sixdd.N_UNIQUE_ID AND lps.V_NAME_OF_VILLAGE = sixdd.v_village\n" +
            "LEFT JOIN\n" +
            "    (SELECT DISTINCT N_UNIQUE_ID, v_village FROM award_possession_taken_over) award\n" +
            "    ON ld.N_UNIQUE_ID = award.N_UNIQUE_ID AND lps.V_NAME_OF_VILLAGE = award.v_village;", nativeQuery = true)
    List<Object[]> mainlistvillageView();
    
    @Query(value="SELECT\r\n"
    		+ "  (SELECT SUM(v_extent_no) FROM 4_one_dynamic_file WHERE N_UNIQUE_ID = :uniqueId ) AS four_one_acres,\r\n"
    		+ "  (SELECT SUM(V_TOTAL_EXTENT) FROM 6dd_file WHERE N_UNIQUE_ID = :uniqueId ) AS six_dd_acres,\r\n"
    		+ "  (SELECT SUM(V_TOTAL_EXTENT) FROM award_file WHERE N_UNIQUE_ID =  :uniqueId ) AS award_file_acres,\r\n"
    		+ "  (SELECT SUM(V_EXTENT) FROM lps_village WHERE N_UNIQUE_ID = :uniqueId ) AS lps_file_acres ,\r\n"
                + " (SELECT SUM(V_TOTAL_EXTENT) FROM award_possession_taken_over WHERE N_UNIQUE_ID = :uniqueId ) AS award_possession_taken_over, \r\n"
                + " (SELECT SUM(V_TOTAL_EXTENT) FROM award_possession_not_taken_over WHERE N_UNIQUE_ID = :uniqueId ) AS award_possession_not_taken_over ", nativeQuery = true)
    List<Object[]> getExtendAcresDetails(String uniqueId);
    
}
