package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "award_other_file")
public class AwardOtherFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "N_FILE_ID")
    private Long N_FILE_ID;

    @Column(name = "V_FILE_NAME")
    private String V_FILE_NAME;

    @Column(name = "V_FILE_PATH")
    private String V_FILE_PATH;

    @Column(name = "V_LEGAL_PROCEEDING")
    private String V_LEGAL_PROCEEDING;

    @Column(name = "V_EXTENT")
    private String V_EXTENT;



    @Transient
    private String mode;

    @Transient
    private String file;

}
