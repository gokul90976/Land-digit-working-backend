package com.bocxy.landDigit.core.landDigitV2.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "award_file")
public class AwardFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private Long N_ID;

    @Column(name = "N_UNIQUE_ID")
    private Long N_UNIQUE_ID;

    @Column(name = "V_AWARD_NO")
    private String V_AWARD_NO;

    @Column(name = "D_AWARD_DATE")
    private String D_AWARD_DATE;

    @Column(name = "V_FILE_NAME")
    private String V_FILE_NAME;

    @Column(name = "V_FILE_PATH")
    private String V_FILE_PATH;

    @Column(name = "N_TOTAL_AWARD_AMOUNT")
    private double N_TOTAL_AWARD_AMOUNT;

    @Column(name = "V_TOTAL_EXTENT")
    private String V_TOTAL_EXTENT;

    @Column(name = "V_PHO_TOTAL_EXTENT")
    private String V_PHO_TOTAL_EXTENT;

    @Column(name = "V_LHO_TOTAL_EXTENT")
    private String V_LHO_TOTAL_EXTENT;

    @Column(name = "V_LNHO_TOTAL_EXTENT")
    private String V_LNHO_TOTAL_EXTENT;

    @Column(name = "V_PNHO_TOTAL_EXTENT")
    private String V_PNHO_TOTAL_EXTENT;

    @Column(name = "V_PHO_SCHEME_TOTAL_EXTENT")
    private String V_PHO_SCHEME_TOTAL_EXTENT;

    @Column(name = "V_LHO_FILE_NAME")
    private String V_LHO_FILE_NAME;

    @Column(name = "V_LHO_FILE_PATH")
    private String V_LHO_FILE_PATH;

    @Column(name = "V_FUTURE_DEV_EXTENT")
    private String futureDevExtent;

    @Column(name = "V_NOT_UTILISED_EXTENT")
    private String notUtilisedExtent;

    @Column(name = "V_NOT_UTILISED_LHO_EXTENT_LIST")
    private String notUtilisedLhoExtentList;


    @Column(name = "V_LNHO_EXTENT_1")
    private String lnhoExtent1;

    @Column(name = "V_LNHO_UTILISED_EXTENT")
    private String lnhoUtilisedExtent;

    @Column(name = "V_LNHO_EXTENT_LIST")
    private String lnhoExtentList;

    @Column(name = "V_LHO_EXTENT_1")
    private String lhoExtent1;

    @Column(name = "V_UTILISED_EXTENT")
    private String utilisedExtent;


    @Transient
    private String mode;

    @Transient
    private String file;

    @Transient
    private String Lhofile;




    // Add Dynamic Files Button Array
    @Transient
    private List<AwardOtherFileEntity> awardOtherFileEntityValuesDetails;

    // Add Dynamic Values Button Array
    @Transient
    private List<DynamicValueEntity> dynamicValuesDetails;

    // Add Dynamic Direct Payment Button Array
    @Transient
    private List<AwardDirectPaymentEntity> awardDirectPaymentEntityValuesDetails;

    // Add Dynamic Revenue Payment Button Array
    @Transient
    private List<AwardRevenuePaymentEntity> awardRevenuePaymentEntityValuesDetails;

    // Add Dynamic Court Deposit Payment Button Array
    @Transient
    private List<AwardCourtDepositPaymentEntity> awardCourtDepositPaymentEntityValuesDetails;

    // Add Dynamic Possession taken over Button Array
    @Transient
    private List<AwardPossessionTakenOverEntity> awardPossessionTakenOverEntityValuesDetails;

    // Add Dynamic Possession not taken over Button Array
    @Transient
    private List<AwardPossessionNotTakenOverEntity> awardPossessionNotTakenOverEntityValuesDetails;

    // Add Dynamic Possession not taken over Button Array
    @Transient
    private List<AwardPossessionExtentAvailableEntity> awardPossessionExtentAvailableEntityValuesDetails;


    @Transient
    private  List<awardnotUtilisedLhoSelectedExtentList> notUtilisedLhoSelectedExtentList;

    @Transient
     private List<awardUtilisedLhoSelectedExtentList>awardUtilisedLhoSelectedExtentList;


}
