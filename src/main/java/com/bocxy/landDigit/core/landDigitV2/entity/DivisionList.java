package com.bocxy.landDigit.core.landDigitV2.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DivisionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Division")
    private String Division;

    @Column(name="District")
    private String District;

    @Column(name="Circle")
    private String Circle;

    @Column(name="City")
    private String City;


    @Column(name="Village")
    private String Village;
}
