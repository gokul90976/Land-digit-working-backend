package com.bocxy.landDigit.core.landDigitV2.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "6dd_dynamic_file")
public class SixDdDynamicFileEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "N_ID")
   private Long N_ID;

   @Column(name = "V_EXTENT")
   private String V_EXTENT;

   @Column(name = "N_UNIQUE_ID")
   private Long N_UNIQUE_ID;

   @Column(name = "V_SURVEY_NO")
   private String V_SURVEY_NO;

   @Column(name = "N_FILE_ID")
   private Long N_FILE_ID;

   @Column(name = "V_NAME_OF_OWNER")
   private String V_NAME_OF_OWNER;

   @Column(name = "V_VILLAGE")
   private String V_VILLAGE;

//   @Column(name = "V_OLD_COLUMN")
//   private String V_OLD_COLUMN;

   @Transient
   private String mode;

}
