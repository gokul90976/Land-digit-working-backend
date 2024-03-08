package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "award_court_deposit_payment")
public class AwardCourtDepositPaymentEntity {

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

    @Transient
    private String mode;

}
