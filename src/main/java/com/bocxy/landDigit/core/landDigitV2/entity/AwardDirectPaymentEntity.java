package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "award_direct_payment")
public class AwardDirectPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "N_FILE_ID")
    private Long N_FILE_ID;

    @Column(name = "V_AMOUNT")
    private String V_AMOUNT;

    @Column(name = "V_NOTIFIED_PERSON")
    private String V_NOTIFIED_PERSON;

    @Column(name = "V_VILLAGE")
    private String V_VILLAGE;

    @Column(name = "V_OLD_COLUMN")
    private String V_OLD_COLUMN;

    @Transient
    private String mode;

    // Add constructors, getters, and setters

}
