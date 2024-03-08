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
@Table(name = "left_over_lps_4_one")
public class LeftOverLPS4OneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "N_ID")
    private Long primarykey;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_SURVEY_NO")
    private String V_SURVEY_NO;

    @Column(name = "V_EXTENT")
    private String V_EXTENT;

    @Transient
    private String mode;

    // @Transient
    // private Long primarykey;

    // Add constructors, getters, and setters

    // Left over /w 4(1) and 6DD Values Button Array
    @Transient
    private List<Left4One6DDEntity> left4One6DDEntityDetails;

    // Left over /w 4(1) and 6DD Values Button Array
    @Transient
    private List<Left6DDAwardEntity> left6DDAwardRepoEntityDetails;

}
