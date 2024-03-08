package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "land_digit_data_division_wise")
public class LandDigitDataDivisionWiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_NAME_OF_CIRCLE")
    private String V_NAME_OF_CIRCLE;

    @Column(name = "V_NAME_OF_DIVISION")
    private String V_NAME_OF_DIVISION;

    @Column(name = "V_NAME_OF_DISTRICT")
    private String V_NAME_OF_DISTRICT;

    @Column(name = "V_CITY_OR_RURAL")
    private String V_CITY_OR_RURAL;

    @Column(name = "V_NAME_OF_VILLAGE")
    private String V_NAME_OF_VILLAGE;

}
