package com.bocxy.landDigit.core.landDigitV2.model;

import java.util.List;

import com.bocxy.landDigit.core.landDigitV2.entity.*;

import lombok.Data;

@Data
public class LandDigitV2SaveModel {


    // First Tab
    private LandDigitDataEntity landDigitDataEntity;
    
    // Media Tab Save
    
    private List<LandDigitMediaEntity> landDigitMediaFiles;

    // Second Tab LPS
    private List<LPSFileEntity> lpsTabDetails;

    // Third Tab 4(1)
    private List<FourOneEntity> fouroneTabDetails;

    // Fourth Tab 6dd
    private List<SixDdFileEntity> sixddTabDetails;

    // Fifth Tab Award
    private List<AwardFileEntity> awardTabDetails;

    // Sixth Tab Award
    private LastTabSaveDetails leftoverTabDetails;



}
