
package com.bocxy.landDigit.core.landDigitV2.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;


@Data
@Getter
@Entity

@Table(name="award_utilised_lhno_selected_extent_list")
public class AwardUtilisedLnhoSelectedExtentList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "N_FILE_ID")
    private Long N_FILE_ID;

    @Column(name = "V_NAME")
    private String V_NAME;

    @Column(name = "V_LHNOSELECTEDEXTENTINLIST")
    private String V_LHONSELECTEDEXTENTINLIST;

    @Column(name = "V_NAME_DATA")
    private String V_NAME_DATA;

    @Column(name = "V_EXTEND")
    private Long V_EXTEND;

    @Transient
    private String mode;
}
