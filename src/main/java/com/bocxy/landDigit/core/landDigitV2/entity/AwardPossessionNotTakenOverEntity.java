package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "award_possession_not_taken_over")
public class AwardPossessionNotTakenOverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "N_FILE_ID")
    private Long N_FILE_ID;

    @Column(name = "V_SURVEY_NO")
    private String V_SURVEY_NO;

    @Column(name = "V_TOTAL_EXTENT")
    private String V_TOTAL_EXTENT;

    @Column(name = "V_VILLAGE")
    private String V_VILLAGE;

    @Transient
    private String mode;

}
