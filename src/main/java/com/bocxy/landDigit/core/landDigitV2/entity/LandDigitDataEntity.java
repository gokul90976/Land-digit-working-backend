package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "land_digit_data")
public class LandDigitDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_NAME_OF_CIRCLE")
    private String V_NAME_OF_CIRCLE;

    @Column(name = "V_NAME_OF_DIVISION")
    private String V_NAME_OF_DIVISION;

    @Column(name = "V_NAME_OF_DISTRICT")
    private String V_NAME_OF_DISTRICT;

    @Column(name = "V_NAME_OF_SCHEME")
    private String V_NAME_OF_SCHEME;

    @Transient
    private String mode;


}