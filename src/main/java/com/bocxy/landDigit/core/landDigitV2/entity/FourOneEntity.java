package com.bocxy.landDigit.core.landDigitV2.entity;

import java.util.List;

import javax.persistence.*;

import com.bocxy.landDigit.core.landDigitV2.model.LandDigitV2SaveRequest;
import lombok.Data;

@Entity
@Data
@Table(name = "4_one")
public class FourOneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_GAZETTE_REF_NO")
    private String V_GAZETTE_REF_NO;

    @Column(name = "V_4_ONE_GO_REF_NO")
    private String V_4_ONE_GO_REF_NO;

    @Column(name = "D_DATE_OF_4_ONE_GO")
    private String D_DATE_OF_4_ONE_GO;

    @Column(name = "V_TOTAL_EXTENT")
    private String V_TOTAL_EXTENT;

    @Column(name = "V_FILE_1_FILENAME")
    private String V_FILE_1_FILENAME;

    @Column(name = "V_FILE_2_FILENAME")
    private String V_FILE_2_FILENAME;

    @Column(name = "V_FILE_1_FILEPATH")
    private String V_FILE_1_FILEPATH;

    @Column(name = "V_FILE_2_FILEPATH")
    private String V_FILE_2_FILEPATH;

    @Transient
    private String mode;

    @Transient
    private String file1;

    @Transient
    private String file2;

    @Transient
    private List<LandDigitV2SaveRequest> files;


    // Add Dynamic Files Button Array
    @Transient
    private List<FourOneDynamicFileEntity> fourOneDynamicFileEntityDetails;

    // Add Dynamic Values Button Array
    @Transient
    private List<DynamicValueEntity> dynamicValuesDetails;


}
