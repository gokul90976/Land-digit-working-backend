package com.bocxy.landDigit.core.landDigitV2.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DivisionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String Division;


    private String District;


    private String circle;


    private String City;



    private String Village;
}
