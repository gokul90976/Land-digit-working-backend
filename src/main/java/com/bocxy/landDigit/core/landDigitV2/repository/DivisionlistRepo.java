package com.bocxy.landDigit.core.landDigitV2.repository;

import com.bocxy.landDigit.core.landDigitV2.entity.DivisionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableJpaRepositories
@Repository
    public interface DivisionlistRepo extends JpaRepository<DivisionList, Long> {
    @Query(value = "SELECT * FROM DivisionList dl WHERE dl.division = :division AND dl.district = :district AND dl.village = :village", nativeQuery = true)
    List<DivisionList> selectByDivisionAndDistrictAndVillage(String division, String district, String village);

    @Query(value = "SELECT * FROM DivisionList dl WHERE dl.circle = :circle AND dl.district = :district AND dl.village = :village", nativeQuery = true)
    List<DivisionList> selectByCircleAndDistrictAndVillage(String circle, String district, String village);

    @Query(value = "SELECT * FROM DivisionList dl WHERE dl.circle = :circle AND dl.division = :division AND dl.district = :district", nativeQuery = true)
    List<DivisionList> selectCircleAndDivisionAndDistrict(String circle, String division, String district);

    @Query(value = "SELECT * FROM division_list WHERE circle = :circle", nativeQuery = true)
    List<DivisionList> findByCircle(@Param("circle") String circle);
    @Query(value = "SELECT * FROM division_list WHERE circle = :circle AND division = :division", nativeQuery = true)
    List<DivisionList> findByCircleAndDivision(@Param("circle") String circle, @Param("division") String division);

    @Query(value = "SELECT * FROM division_list WHERE circle = :circle AND division = :division AND district = :district", nativeQuery = true)
    List<DivisionList> findByCircleAndDivisionAndDistrict(@Param("circle") String circle, @Param("division") String division, @Param("district") String district);
}





