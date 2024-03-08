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
@Table(name = "dynamic_value")
public class DynamicValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_COLUMN_NAME")
    private String V_COLUMN_NAME;

    @Column(name = "V_VALUE_NAME")
    private String V_VALUE_NAME;

    @Column(name = "N_FILE_ID")
    private Long N_FILE_ID;

    @Column(name = "V_FILE_NAME")
    private String V_FILE_NAME;

    @Transient
    private String mode;

}
