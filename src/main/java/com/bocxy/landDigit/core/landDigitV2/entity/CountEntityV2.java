package com.bocxy.landDigit.core.landDigitV2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Counttable")
public class CountEntityV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "V_NAME_OF_CIRCLE")
    private String V_NAME_OF_CIRCLE;

    @Column(name = "V_NAME_OF_DIVISION")
    private String V_NAME_OF_DIVISION;
    @Column(name = "V_CITY_OR_RURAL")
    private String V_CITY_OR_RURAL;

    @Column(name = "V_TOTAL_COUNT")
    private String V_TOTAL_COUNT;

}
