package com.bocxy.landDigit.core.landDigitV2.model;

import java.util.List;

import com.bocxy.landDigit.core.landDigitV2.entity.Left4One6DDEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.Left6DDAwardEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.LeftOverLPS4OneEntity;

import lombok.Data;

@Data
public class LastTabSaveDetails {


    private List<LeftOverLPS4OneEntity> leftOverLPS4OneEntity;
    private List<Left6DDAwardEntity> left6DDAwardEntity;
    private List<Left4One6DDEntity> left4One6DDEntity;


}
