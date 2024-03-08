package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "4_one_dynamic_file")
public class FourOneDynamicFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_SURVEY_NO")
    private String V_SURVEY_NO;

    @Column(name = "v_EXTENT_NO")
    private String v_EXTENT_NO;

    @Column(name = "N_FILE_ID")
    private Long N_FILE_ID;

    @Column(name = "V_NAME_OF_OWNER")
    private String V_NAME_OF_OWNER;

    @Column(name = "V_FILE_NAME")
    private String V_FILE_NAME;

    @Column(name = "V_FILE_PATH")
    private String V_FILE_PATH;

    @Column(name = "V_VILLAGE")
    private String V_VILLAGE;

    @Transient
    private String file;

    @Transient
    private String mode;

}
