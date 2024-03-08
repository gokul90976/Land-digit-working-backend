package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "left_4_one_6dd")
public class Left4One6DDEntity {

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

    // Add constructors, getters, and setters

}
