package com.bocxy.landDigit.core.landDigitV2.entity;

import java.util.List;

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
@Table(name = "lps_file")
public class LPSFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_FILE_NAME")
    private String V_FILE_NAME;

    @Column(name = "V_FILE_PATH")
    private String V_FILE_PATH;

    @Column(name = "V_REF_NO")
    private String V_REF_NO;

    @Column(name = "V_TOTAL_EXTENT")
    private String V_TOTAL_EXTENT;

    @Transient
    private String mode;

    @Transient
    private String file;

    // Add Village Button Array
    @Transient
    private List<LPSVillageEntity> lpsVillageDetails;

    // Add Dynamic Files Button Array
    @Transient
    private List<LPSFileDynamicValueEntity> lpsFileDynamicValuesDetails;

    // Add Dynamic Values Button Array
    @Transient
    private List<DynamicValueEntity> dynamicValuesDetails;

}
