package com.bocxy.landDigit.core.landDigitV2.model;

import java.util.List;
import com.bocxy.landDigit.core.landDigitV2.entity.AwardFileEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.FourOneEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.LPSFileEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.LandDigitDataEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.LandDigitMediaEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.SixDdFileEntity;

import lombok.Data;

@Data
public class LandDigitV2ResponseModel {

    // first Tab
    private LandDigitDataEntity landDigitDataEntity;

    // First Tab Media
    private List<LandDigitMediaEntity> landDigitMediaFiles;

    // Second Tab
    private List<LPSFileEntity> lpsTabDetails;
    
    // Third Tab
    private List<FourOneEntity> fourOneTabDeatils;
    
    // Fourth Tab
    private List<SixDdFileEntity> sixDdTabDeatils;
    
    // Fifth Tab
    private List<AwardFileEntity> awardTabDeatils;

    // Sixth Tab
    private LastTabSaveDetails leftoverTabDeatils;

    // completed

    
}