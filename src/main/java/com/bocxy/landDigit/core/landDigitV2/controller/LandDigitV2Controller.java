package com.bocxy.landDigit.core.landDigitV2.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bocxy.landDigit.core.landDigitV2.entity.*;
import com.bocxy.landDigit.core.landDigitV2.repository.DivisionlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bocxy.landDigit.core.common.ResponseDo;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitCountVillageViewV2;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitListViewV2;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitListVillageViewV2;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitV2ResponseModel;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitV2SaveModel;
import com.bocxy.landDigit.core.landDigitV2.service.LandDigitV2Service;

import net.minidev.json.JSONObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LandDigitV2Controller {

    @Autowired
    LandDigitV2Service landDigitV2DetailsService;

    @Autowired
    DivisionlistRepo divisionlist;
    @Autowired
    ResponseDo responseDo;

    //Get
    @PostMapping("/getAllLandDigitDatas")
    public ResponseDo getAllLanddetails(@RequestBody JSONObject json, final HttpServletRequest request,
            HttpServletResponse response) {

        Long id = json.getAsNumber("id").longValue();

        try {
            LandDigitV2ResponseModel responseModel = landDigitV2DetailsService.getAllLandDigitDetails(id);

            if (responseModel != null) {
                return responseDo.setSuccessResponse(responseModel);
            } else {
                return responseDo.setSuccessResponse("No Data Found", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDo;

    }



    //Report
//    @PostMapping("/getA")
//    public ResponseDo getAllLanddetailss(@RequestBody JSONObject json, final HttpServletRequest request,
//                                        HttpServletResponse response) {
//
//        Long id = json.getAsNumber("id").longValue();
//
//        try {
//            List<AwardFileEntity> responseModel = landDigitV2DetailsService.AwardReport(id);
//
//            if (responseModel != null) {
//                return responseDo.setSuccessResponse(responseModel);
//            } else {
//                return responseDo.setSuccessResponse("No Data Found", null);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return responseDo;
//
//    }



    //Save
    @PostMapping("/saveUpdateLandData")
    public ResponseDo saveUpdate(@RequestBody LandDigitV2SaveModel saveModel, final HttpServletRequest request,
            HttpServletResponse response) {

        try {
            System.out.println(saveModel);
            String saveResponse = landDigitV2DetailsService.saveUpdateLandDatas(saveModel);

            if (saveResponse != null) {
                return responseDo.setSuccessResponse(saveResponse);
            } else {
                return responseDo.setSuccessResponse("Save Failure", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDo;

    }
    
    
    //Delete
    @PostMapping("/deleteV2LandData")
    public ResponseEntity<String> deleteLandDigitData(@RequestBody JSONObject json, final HttpServletRequest request,
            HttpServletResponse response) {

        Long id = json.getAsNumber("id").longValue();

        try {
            landDigitV2DetailsService.deleteV2LandDetailsById(id);
            ResponseEntity.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity.notFound();
        return null;
    }

    //Home Screen List
    @PostMapping("/GetData")
    public List<LandDigitListViewV2> mainView(@RequestBody JSONObject json, final HttpServletRequest request,
                                              HttpServletResponse response) {

        String types = json.getAsString("types");
        String values = json.getAsString("values");
        List<LandDigitListViewV2> listData = landDigitV2DetailsService.mainView(types, values);

        return listData;
    }
    
    //Home Screen Count
    @PostMapping("/GetDataCount")
    public List<CountEntityV2> getAllemployeess(@RequestBody JSONObject json, final HttpServletRequest request,
            HttpServletResponse response) {

        String types = json.getAsString("types");
        String values = json.getAsString("values");
        List<CountEntityV2> listData = landDigitV2DetailsService.getviewcount(types, values);
        return listData;
    }



    //Report Village Count
    @PostMapping("/GetCountDataVillage")
    public List<LandDigitCountVillageViewV2> maincountvillageView(@RequestBody JSONObject json, final HttpServletRequest request,
                                                                  HttpServletResponse response) {

        String types = json.getAsString("types");
        String values = json.getAsString("values");
        List<LandDigitCountVillageViewV2> listData = landDigitV2DetailsService.maincountvillageView(types, values);
        return listData;
    }

//    @PostMapping("/Getwarddetails")
//    public ResponseDo<List<AwardFileEntity>> Getwarddetails(@RequestBody JSONObject json, final HttpServletRequest request, HttpServletResponse response) {
//        try {
//            Long id = null;
//            if (json.containsKey("id")) {
//                id = json.getAsNumber("id").longValue();
//                landDigitV2DetailsService.AwardReport(id);
//                // Assuming landDigitV2DetailsService.AwardReport(id) returns successfully
//                return responseDo.setSuccessResponse("Award details fetched successfully");
//            } else {
//                return responseDo.setFailureResponse("Missing 'id' field in the request JSON");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return responseDo.setFailureResponse("An error occurred: " + e.getMessage());
//        }
//    }





    //Report Village List
    @PostMapping("/GetListDataVillage")
    public List<LandDigitListVillageViewV2> mainlistvillageView(@RequestBody JSONObject json, final HttpServletRequest request,
                                                                  HttpServletResponse response) {

        String types = json.getAsString("types");
        String values = json.getAsString("values");
        List<LandDigitListVillageViewV2> listData = landDigitV2DetailsService.mainlistvillageView(types, values);
        return listData;
    }
    
     // Save Image test
    @PostMapping("/uploadMultipleMedias")
    public ResponseDo uplaodImageArray(@RequestParam("files") MultipartFile[] files,  @RequestParam("N_UNIQUE_ID") String N_UNIQUE_ID) throws IOException {

        try {

            String response = landDigitV2DetailsService.saveImagesAndVideos(files,N_UNIQUE_ID);

            return responseDo.setResponse(0, "Upload Succesfully", null);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return responseDo.setFailureResponse("upload failure");
    }

    
    // Get images
    @PostMapping("getMedia")
    public ResponseDo getImages(@RequestBody JSONObject json, final HttpServletRequest request,
            HttpServletResponse response) {

        Long id = json.getAsNumber("id").longValue();
        String values = json.getAsString("values");

        try {

             Optional<LandDigitMediaEntity> result = landDigitV2DetailsService.getImages(id);
            return responseDo.setSuccessResponse(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDo.setFailureResponse("File Not Found");
    }


    @GetMapping("/all")
    public List<AwardFileEntity> getAllAwardFiles() {
        return landDigitV2DetailsService.getAllAwardFiles();
    }

    @PostMapping("/getAllAwardFiles")
    public ResponseDo getAllAwardFiles(@RequestBody JSONObject json, final HttpServletRequest request,
                                        HttpServletResponse response) {

        Long id = json.getAsNumber("id").longValue();

        try {
            List<AwardFileEntity> responseModel = landDigitV2DetailsService.getAllAwardFiles();

            if (responseModel != null) {
                return responseDo.setSuccessResponse(responseModel);
            } else {
                return responseDo.setSuccessResponse("No Data Found", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDo;

    }


//    @GetMapping("/totals")
//    public List<Object[]> getAwardFileWithTotals() {
//        return landDigitV2DetailsService.getAwardFileWithTotals();
//    }



    @GetMapping("/totals")
    public List<Map<String, Object>> getAwardFileWithTotals() {
        return landDigitV2DetailsService.getAwardFileWithTotals();
    }

    //Aws
    @GetMapping("/awsconfig")
    public AwsConfig getAwsConfig() {
        return landDigitV2DetailsService.getAwsConfig();
    }


    @GetMapping("/Circle")
    public List<String> getAllDistricts() {
        return divisionlist.findAll().stream()
                .map(DivisionList::getCircle)
                .distinct()
                .collect(Collectors.toList());
    }

    @GetMapping("/Division/{Circle}")
    public List<String> getDivisionsByCircle(@PathVariable("Circle") String circle) {
        return divisionlist.findByCircle(circle).stream()
                .map(DivisionList::getDivision)
                .distinct()
                .collect(Collectors.toList());
    }
    @GetMapping("/District/{Circle}/{Division}")
    public List<String> getVillagesByCircleAndDivision(
            @PathVariable("Circle") String circle,
            @PathVariable("Division") String division) {
        return divisionlist.findByCircleAndDivision(circle, division).stream()
                .map(DivisionList::getDistrict)
                .distinct()
                .collect(Collectors.toList());
    }


    @GetMapping("/Village/{Circle}/{Division}/{District}")
    public List<String> getVillagesByCircleAndDivisionAndDistrict(
            @PathVariable("Circle") String circle,
            @PathVariable("Division") String division,
            @PathVariable("District") String district) {
        return divisionlist.findByCircleAndDivisionAndDistrict(circle, division, district).stream()
                .map(DivisionList::getVillage)
                .distinct()
                .collect(Collectors.toList());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourtCase(@PathVariable Long id) {
        landDigitV2DetailsService.courtcase(id);
        return ResponseEntity.ok("Court case with ID: " + id + " has been deleted successfully.");
    }


    @PostMapping("/not-utilised-lho-selected-extent-list/delete/{id}")
    public ResponseEntity<String> deleteNotUtilisedLhoSelectedExtentList(@PathVariable Long id) {
        landDigitV2DetailsService.awardnotutilisedlhoselectedextentlistrepodeleteById(id);
        return ResponseEntity.ok("Award not utilised Lho selected extent list with ID: " + id + " has been deleted successfully.");
    }

    @PostMapping("/utilised-lho-selected-extent-list/delete/{id}")
    public ResponseEntity<String> deleteUtilisedLhoSelectedExtentList(@PathVariable Long id) {
        landDigitV2DetailsService.awardutilisedlhoselectedextentlistrepodeleteById(id);
        return ResponseEntity.ok("Award utilised Lho selected extent list with ID: " + id + " has been deleted successfully.");
    }

    @PostMapping("/utilised-lnho-selected-extent-list/delete/{id}")
    public ResponseEntity<String> deleteUtilisedLnhoSelectedExtentList(@PathVariable Long id) {
        landDigitV2DetailsService.awardutilisedlnhoselectedextentlistrepodeleteById(id);
        return ResponseEntity.ok("Award utilised Lho selected extent list with ID: " + id + " has been deleted successfully.");
    }

    @PostMapping("/direct-payment/delete/{id}")
    public ResponseEntity<String> deleteDirectPayment(@PathVariable Long id) {
        landDigitV2DetailsService.Direct(id);
        return ResponseEntity.ok("Direct payment with ID: " + id + " has been deleted successfully.");
    }

    @PostMapping("/court-deposit-payment/delete/{id}")
    public ResponseEntity<String> deleteCourtDepositPayment(@PathVariable Long id) {
        landDigitV2DetailsService.courtcase(id);
        return ResponseEntity.ok("Court deposit payment with ID: " + id + " has been deleted successfully.");
    }

    @PostMapping("/revenue-payment/delete/{id}")
    public ResponseEntity<String> deleteRevenuePayment(@PathVariable Long id) {
        landDigitV2DetailsService.revenue(id);
        return ResponseEntity.ok("Revenue payment with ID: " + id + " has been deleted successfully.");
    }





}
