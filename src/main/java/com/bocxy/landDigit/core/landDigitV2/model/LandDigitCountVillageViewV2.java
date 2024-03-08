package com.bocxy.landDigit.core.landDigitV2.model;

import lombok.Data;

@Data
public class LandDigitCountVillageViewV2 {

    private String V_NAME_OF_DIVISION;
    
    private String V_NAME_OF_CIRCLE;

    private String V_VILLAGE;

    private String lps_file_count;

    private String four_one_count;

    private String sixdd_file_count;

    private String award_file_count;
    
    // added for Extend Details
    
    private String unique_Id;
    
    private Double fourOneAcres;
    private Double sixDDAcres;
    private Double awardAcres;
    private Double lpsAcres;
    private Double possissiontakenOver;
    private Double possiosionNotTakenOver;



}
