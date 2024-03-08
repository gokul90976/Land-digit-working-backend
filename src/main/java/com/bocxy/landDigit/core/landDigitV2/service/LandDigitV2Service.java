package com.bocxy.landDigit.core.landDigitV2.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bocxy.landDigit.core.landDigitV2.entity.AwardFileEntity;
import com.bocxy.landDigit.core.landDigitV2.entity.AwsConfig;
import com.bocxy.landDigit.core.landDigitV2.model.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.bocxy.landDigit.core.landDigitV2.entity.CountEntityV2;
import com.bocxy.landDigit.core.landDigitV2.entity.LandDigitMediaEntity;

import javax.persistence.Transient;

public interface LandDigitV2Service {
	
	
    //Get
    public LandDigitV2ResponseModel getAllLandDigitDetails(Long id);

    //Save
    public String saveUpdateLandDatas(LandDigitV2SaveModel saveModel);
    
    //Delete
    public void deleteV2LandDetailsById(Long id);
    
    // Home Screen
    public List<LandDigitListViewV2> mainView(String types, String values);



//    List<AwardFileEntity> getAllAwardFiles();

    List<AwardFileEntity> getAllAwardFiles();


    @Transient
    public List<Map<String, Object>> getAwardFileWithTotals();

    // Home ScreenCount
    public List<CountEntityV2> getviewcount(String types, String values);
    
    // Report Village Count
    public List<LandDigitCountVillageViewV2> maincountvillageView(String types, String values);

    // Report Village List
    public List<LandDigitListVillageViewV2> mainlistvillageView(String types, String values);

    // Load Image 
    public  Optional<LandDigitMediaEntity> getImages(Long id);

     // Save Multiple Images and videos 
    public String saveImagesAndVideos(@RequestParam("files") MultipartFile[] files,String N_UNIQUE_ID) throws IOException;

    AwsConfig getAwsConfig();
}