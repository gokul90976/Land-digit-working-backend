package com.bocxy.landDigit.core.landDigitV2.repository;

import com.bocxy.landDigit.core.landDigitV2.entity.CountEntityV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CountEntityV2Repo extends JpaRepository<CountEntityV2,Long> {
    @Query(value = "SELECT N_ID,V_NAME_OF_CIRCLE,V_NAME_OF_DIVISION,V_CITY_OR_RURAL,V_TOTAL_COUNT FROM counttable WHERE V_NAME_OF_CIRCLE=?1", nativeQuery = true)
    List<CountEntityV2> findByCircle(String value);

    @Query(value = "SELECT N_ID,V_NAME_OF_CIRCLE,V_NAME_OF_DIVISION,V_CITY_OR_RURAL,V_TOTAL_COUNT FROM counttable WHERE V_CITY_OR_RURAL=?1", nativeQuery = true)
    List<CountEntityV2> findByCityNrual(String value);

    @Query(value = "SELECT N_ID,V_NAME_OF_CIRCLE,V_NAME_OF_DIVISION,V_CITY_OR_RURAL,V_TOTAL_COUNT FROM counttable WHERE V_NAME_OF_DIVISION=?1", nativeQuery = true)
    List<CountEntityV2> findByDivision(String value);



}
