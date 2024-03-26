package com.bocxy.landDigit.core.landDigitV2.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class LandDigitListViewV2 {

    private Long N_UNIQUE_ID;

    private String V_NAME_OF_CIRCLE;

    private String V_NAME_OF_DIVISION;

    private  String V_NAME_OF_VILLAGE;

//    private String V_NAME_OF_VILLAGE;

    private String V_NAME_OF_DISTRICT;

    private String V_NAME_OF_SCHEME;

    private String V_TOTAL_EXTENT;

    private String V_PHO_TOTAL_EXTENT;

    private String V_PNHO_TOTAL_EXTENT;

    private String V_PHO_SCHEME_TOTAL_EXTENT;

    private String lps_file_count;

    private String four_one_count;

    private String sixdd_file_count;

    private String award_file_count;

    private String sixdd_total_extent;

    private String fourone_total_extent;

    private String award_court_deposit;

    private String award_civil_court;

    private String  award_direct_payment;

    private String  award_revenue_payment;



    private String futureDevExtent;


    private String notUtilisedExtent;

    private String notUtilisedLhoExtentList;


    private String lnhoExtent1;

    private String lnhoUtilisedExtent;

    private String lnhoExtentList;

    private String lhoExtent1;

    private String utilisedExtent;

    private String N_TOTAL_AWARD_AMOUNT;
}