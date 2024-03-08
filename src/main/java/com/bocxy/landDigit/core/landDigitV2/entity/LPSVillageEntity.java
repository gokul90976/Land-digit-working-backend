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
@Table(name = "lps_village")
public class LPSVillageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_NAME_OF_VILLAGE")
    private String V_NAME_OF_VILLAGE;

    @Column(name = "V_SURVEY_NO")
    private String V_SURVEY_NO;

    @Column(name = "V_EXTENT")
    private String V_EXTENT;

    @Column(name = "N_FILE_ID")
    private Long N_FILE_ID;

    @Transient
    private String mode;

}
