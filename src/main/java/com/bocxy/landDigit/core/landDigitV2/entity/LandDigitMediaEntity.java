package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "LAND_DIGIT_MEDIA")
public class LandDigitMediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String fileName;

    @Column(name = "type")
    private String fileType;

    @Lob
    @Column(name = "media_data")
    private byte[] mediaData;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;
    
    @Transient
    private String mode;
}
