package com.bocxy.landDigit.core.landDigitV2.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import com.bocxy.landDigit.core.landDigitV2.entity.*;
import com.bocxy.landDigit.core.landDigitV2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bocxy.landDigit.core.landDigitV2.model.LandDigitCountVillageViewV2;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitListViewV2;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitListVillageViewV2;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitV2ResponseModel;
import com.bocxy.landDigit.core.landDigitV2.model.LandDigitV2SaveModel;
import com.bocxy.landDigit.core.landDigitV2.model.LastTabSaveDetails;

@Service
public class LandDigitV2ServiceImpl implements LandDigitV2Service {

	@Autowired
	LandDigitv2MainRepo landDigitv2MainRepo;

	@Autowired
	LPSFileRepo lPSFileRepo;

	@Autowired
	LPSVillageRepo lPSVillageRepo;

	@Autowired
	LPSFileDynamicValueRepo lpsFileDynamicValueRepo;

	@Autowired
	DynamicValueRepo dynamicValueRepo;

	@Autowired
	FourOneEntityRepo fourOneEntityRepo;

	@Autowired
	FourOneDynamicFileEntityRepo fourOneDynamicFileEntityRepo;

	@Autowired
	SisDdfileRepo sisDdfileRepo;

	@Autowired
	SixDdDynamicFileRepo sixDdDynamicFileRepo;

	@Autowired
	AwardFileRepo awardFileRepo;

	@Autowired
	AwardOtherFileRepo awardOtherFileRepo;

	@Autowired
	AwardDirectPaymentRepo awardDirectPaymentRepo;

	@Autowired
	AwardRevenuePaymentRepo awardRevenuePaymentRepo;

	@Autowired
	AwardCourtDepositPaymentRepo awardCourtDepositPaymentRepo;

	@Autowired
	AwardPossessionTakenOverRepo awardPossessionTakenOverRepo;

	@Autowired
	AwardPossessionNotTakenOverRepo awardPossessionNotTakenOverRepo;

	@Autowired
	AwardPossessionExtentAvailableRepo awardPossessionExtentAvailableRepo;

	@Autowired
	LeftOverLPS4OneRepo leftOverLPS4OneRepo;

	@Autowired
	Left4One6DDRepo left4One6DDRepo;

	@Autowired
	Left6DDAwardRepo left6DDAwardRepo;

	@Autowired
	LandDigitDataDivisionWiseRepo landDigitDataDivisionWiseRepo;

	@Autowired
	CountEntityV2Repo countEntityV2Repo;

	@Autowired
	LandDigitMediaRepo landDigitMediaRepo;

	@Autowired
	DivisionlistRepo divisionlist;

	@Autowired
	AwardnotUtilisedLhoSelectedExtentListRepo awardnotutilisedlhoselectedextentlistrepo;

	@Autowired
	AwardUtilisedLhoSelectedExtentListRepo awardutilisedlhoselectedextentlistrepo;

	@Value("${aws.accessKeyId}")
	private String accessKeyId;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Value("${aws.region}")
	private String region;

	@Value("${aws.s3.bucketName}")
	private String bucketName;


	@Value("${upload.dir}")
	private String uploadDir;

	// GET 
	@Override
	public LandDigitV2ResponseModel getAllLandDigitDetails(Long uniqueId) {

		LandDigitV2ResponseModel landDigitAllDataResponse = new LandDigitV2ResponseModel();

		// land Details First Tab
		
		Optional<LandDigitDataEntity> landDigitDataEntity = landDigitv2MainRepo.findById(uniqueId);

		// Land Details Media
		List<LandDigitMediaEntity> listOfMediaDetails = landDigitMediaRepo.getAllMediaDetails(uniqueId);
		landDigitAllDataResponse.setLandDigitMediaFiles(listOfMediaDetails);
		int unique_Id = uniqueId.intValue();
		
		if (landDigitDataEntity.isPresent()) {
			LandDigitDataEntity value = landDigitDataEntity.get();
			landDigitAllDataResponse.setLandDigitDataEntity(value);



			List<FourOneEntity> fouroneFileList = fourOneEntityRepo.findByUniqueId(unique_Id);

			for (FourOneEntity fourOneEntity : fouroneFileList) {

				Long file_id = fourOneEntity.getN_ID();
				String file_name = fourOneEntity.getV_FILE_1_FILENAME();

				// 4(1) Dynamic files
				List<FourOneDynamicFileEntity> fourOneDynamicFileDetails = fourOneDynamicFileEntityRepo
						.getByUniqueId(unique_Id, file_id);

				fourOneEntity.setFourOneDynamicFileEntityDetails(fourOneDynamicFileDetails);

				// 4(1) Dynamic Other Details
				List<DynamicValueEntity> dynamicValuesDetails = dynamicValueRepo.getByUniqueId(unique_Id, file_id,
						file_name);
				fourOneEntity.setDynamicValuesDetails(dynamicValuesDetails);

			}

			landDigitAllDataResponse.setFourOneTabDeatils(fouroneFileList);

			// 6DD Fourth Tab

			List<SixDdFileEntity> sixDdFileList = sisDdfileRepo.findByUniqueId(unique_Id);

			System.out.println(sixDdFileList);

			for (SixDdFileEntity sixDdFileEntity : sixDdFileList) {

				Long file_id = sixDdFileEntity.getN_ID();
				String file_name = sixDdFileEntity.getV_FILE_1_FILENAME();

				// 6DD Dynamic files
				List<SixDdDynamicFileEntity> sixDdDynamicFileDetails = sixDdDynamicFileRepo.getByUniqueId(unique_Id,
						file_id);
				sixDdFileEntity.setSixDdDynamicFileEntityValuesDetails(sixDdDynamicFileDetails);

				// 6DD Dynamic Other Details
				List<DynamicValueEntity> dynamicValuesDetails = dynamicValueRepo.getByUniqueId(unique_Id, file_id,
						file_name);
				sixDdFileEntity.setDynamicValuesDetails(dynamicValuesDetails);

			}

			landDigitAllDataResponse.setSixDdTabDeatils(sixDdFileList);

			// Award Fifth Tab

			List<AwardFileEntity> awardFileList = awardFileRepo.findByUniqueId(unique_Id);

			for (AwardFileEntity awardFileEntity : awardFileList) {

				Long file_id = awardFileEntity.getN_ID();
				String file_name = awardFileEntity.getV_FILE_NAME();

				// Award Dynamic files
				List<AwardOtherFileEntity> awardOtherFileDetails = awardOtherFileRepo.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardOtherFileEntityValuesDetails(awardOtherFileDetails);

				// Award Dynamic Other Details
				List<DynamicValueEntity> dynamicValuesDetails = dynamicValueRepo.getByUniqueId(unique_Id, file_id,
						file_name);
				awardFileEntity.setDynamicValuesDetails(dynamicValuesDetails);

				// Award Direct Payment Details
				List<AwardDirectPaymentEntity> awardDirectPaymentDetails = awardDirectPaymentRepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardDirectPaymentEntityValuesDetails(awardDirectPaymentDetails);

				// Award Revenue Payment Details
				List<AwardRevenuePaymentEntity> awardRevenuePaymentDetails = awardRevenuePaymentRepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardRevenuePaymentEntityValuesDetails(awardRevenuePaymentDetails);

				// Award Court deposit Payment Details
				List<AwardCourtDepositPaymentEntity> awardCourtDepositPaymentDetails = awardCourtDepositPaymentRepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardCourtDepositPaymentEntityValuesDetails(awardCourtDepositPaymentDetails);

				// Award Possession taken over Details
				List<AwardPossessionTakenOverEntity> awardPossessionTakenOverDetails = awardPossessionTakenOverRepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardPossessionTakenOverEntityValuesDetails(awardPossessionTakenOverDetails);

				// Award Not Possession taken over Details
				List<AwardPossessionNotTakenOverEntity> awardPossessionNotTakenOverDetails = awardPossessionNotTakenOverRepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardPossessionNotTakenOverEntityValuesDetails(awardPossessionNotTakenOverDetails);

				// Award Not Utilized
				List<AwardnotUtilisedLhoSelectedExtentList> awardnotutilisedlhoselectedextentlist = awardnotutilisedlhoselectedextentlistrepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardnotUtilisedLhoSelectedExtentList(awardnotutilisedlhoselectedextentlist);

				// Award  Utilized
				List<AwardUtilisedLhoSelectedExtentList> awardutilisedlhoselectedextentlist = awardutilisedlhoselectedextentlistrepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity.setAwardUtilisedLhoSelectedExtentList(awardutilisedlhoselectedextentlist);

				// Award Possession extent available Details
				List<AwardPossessionExtentAvailableEntity> awardPossessionExtentAvailableDetails = awardPossessionExtentAvailableRepo
						.getByUniqueId(unique_Id, file_id);
				awardFileEntity
						.setAwardPossessionExtentAvailableEntityValuesDetails(awardPossessionExtentAvailableDetails);

			}

			landDigitAllDataResponse.setAwardTabDeatils(awardFileList);

			// Left Over Sixth Tab

			LastTabSaveDetails lastTabDetails = new LastTabSaveDetails();
			List<LeftOverLPS4OneEntity> leftOverLPS4OneList = leftOverLPS4OneRepo.findByUniqueId(unique_Id);
			lastTabDetails.setLeftOverLPS4OneEntity(leftOverLPS4OneList);

			// Left Over b/w 4(1) and 6DD
			List<Left4One6DDEntity> leftOver4One6DDEntityDetails = left4One6DDRepo.findByUniqueId(unique_Id);
			lastTabDetails.setLeft4One6DDEntity(leftOver4One6DDEntityDetails);

			// Left Over b/w 6DD and Award
			List<Left6DDAwardEntity> left6DDAwardEntityDetails = left6DDAwardRepo.findByUniqueId(unique_Id);
			lastTabDetails.setLeft6DDAwardEntity(left6DDAwardEntityDetails);

			landDigitAllDataResponse.setLeftoverTabDeatils(lastTabDetails);

		} else {
			return null;
		}

		return landDigitAllDataResponse;
	}


	// TO FIND OUT THE EXTENSION FOR AWARD TAB FILES
	private String getFileExtensionFromSignature(byte[] data) throws IOException {
		try (BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(data))) {
			int signature = (bis.read() & 0xFF) << 8 | (bis.read() & 0xFF);
			switch (signature) {
				case 0xFFD8: return "jpg";
				case 0x8950: return "png";
				case 0x4749: return "gif";
				case 0x2550: return "pdf";
				case 0x504B: return "zip";
				case 0xD0CF: {
					// This case is for detecting MS Office files (DOC, XLS, PPT)
					// The second part of the signature is used to distinguish specific types
					int part2 = bis.read() & 0xFF;
					if (part2 == 0xE1) return "doc";
					if (part2 == 0xE2) return "xls";
					if (part2 == 0xE3) return "ppt";
					break;
				}
				case 0x504B0304: return "xlsx"; 
			}
		}
		return ""; 
	}

	
	// Save API
	
	@Transactional
	@Override
	public String saveUpdateLandDatas(LandDigitV2SaveModel saveModel) {

		try {

			Long uniqueCode = saveModel.getLandDigitDataEntity().getN_UNIQUE_ID();
			Long createdFileId = 0l;
			String createdFileName = "";
			Long createdUniqueCode = 0l;

			// First Tab Land Details Save Update Delete
			LandDigitDataEntity landDetailsFirstTab = saveModel.getLandDigitDataEntity();
			String mode = saveModel.getLandDigitDataEntity().getMode();

			//Edit
			if (mode.equals("edit")) {
				LandDigitDataEntity createdObj = landDigitv2MainRepo.save(landDetailsFirstTab);
				createdUniqueCode = createdObj.getN_UNIQUE_ID();
			}

			// Delete
			if (mode.equals("delete")) {
				landDigitv2MainRepo.deleteById(uniqueCode);
			}


			List<FourOneEntity> fourOneDeatils = saveModel.getFouroneTabDetails();

			if (fourOneDeatils != null) {

				int serialNumber = 1;
				for (int i = 0; i < fourOneDeatils.size(); i++) {
					FourOneEntity fourOneObject = fourOneDeatils.get(i);
					String fouroneMode = fourOneObject.getMode();
					Long fouroneFileId = fourOneObject.getN_ID();
					

					if (fouroneFileId == null && fouroneMode.equals("create")) {
                        fourOneObject.setN_UNIQUE_ID(createdUniqueCode);
						FourOneEntity savedData = fourOneEntityRepo.save(fourOneObject);
						createdFileId = savedData.getN_ID();
						createdFileName = savedData.getV_FILE_1_FILENAME();

                    }

					// Update Entity
					else if (fouroneMode.equals("edit")) {

						fourOneEntityRepo.save(fourOneObject);
					}

					// Delete
					if (fouroneFileId!= null &&  fouroneMode.equals("delete")) {
						fourOneEntityRepo.deleteById(fouroneFileId);
					}

					// !4(1) Third Tab Dynamic values Save Update Delete
					List<DynamicValueEntity> dynamicValueListThirdTab = fourOneObject.getDynamicValuesDetails();
					
					if (dynamicValueListThirdTab != null) {

						for (DynamicValueEntity dynamicValueObj : dynamicValueListThirdTab) {
							String dynamicvalueMode = dynamicValueObj.getMode();
							Long dynamicvalueId = dynamicValueObj.getN_ID();
							
							// Save Entity
							if (dynamicvalueId == null && dynamicvalueMode.equals("create")) {
								dynamicValueObj.setN_UNIQUE_ID(createdUniqueCode);
								dynamicValueObj.setN_FILE_ID(createdFileId);
								dynamicValueObj.setV_FILE_NAME(createdFileName);
								dynamicValueRepo.save(dynamicValueObj);
								} 
							
							// Update Entity
							else if (dynamicvalueMode.equals("edit")) {
								dynamicValueRepo.save(dynamicValueObj);
							}

							// Delete
							if (dynamicvalueId != null && dynamicvalueMode.equals("delete")) {
								dynamicValueRepo.deleteById(dynamicvalueId);
							}
						}

					} else {

					}

					// !4(1) Third Tab Dynamic Files Save Update Delete
					List<FourOneDynamicFileEntity> fourOneDynamicFileList = fourOneObject.getFourOneDynamicFileEntityDetails();
					if (fourOneDynamicFileList != null) {
						for (FourOneDynamicFileEntity fourOneDynamicValueObj : fourOneDynamicFileList) {
							String fouroneFileDynamicValueMode = fourOneDynamicValueObj.getMode();
							Long fouroneFileDynamicValueId = fourOneDynamicValueObj.getN_ID();


							// Save Entity
							if (fouroneFileDynamicValueId == null && fouroneFileDynamicValueMode.equals("create")) {
								fourOneDynamicValueObj.setN_UNIQUE_ID(createdUniqueCode);
								fourOneDynamicValueObj.setN_FILE_ID(createdFileId);
								fourOneDynamicValueObj.setSerialNumber(createdFileId);
                                fourOneDynamicFileEntityRepo.save(fourOneDynamicValueObj);
							} 
							
							// Update Entity
							else if (fouroneFileDynamicValueMode.equals("edit")) {
								fourOneDynamicFileEntityRepo.save(fourOneDynamicValueObj);
							}

							// Delete
							if (fouroneFileDynamicValueId != null && fouroneFileDynamicValueMode.equals("delete")) {
								fourOneDynamicFileEntityRepo.deleteById(fouroneFileDynamicValueId);
							}
						}

					} else {

					}

				}
			} else {

			}

			// Fourth Tab start here
			
			List<SixDdFileEntity> sixDdFileDeatils = saveModel.getSixddTabDetails();

			if (sixDdFileDeatils != null) {

				for (int i = 0; i < sixDdFileDeatils.size(); i++) {
					SixDdFileEntity sixDdFileObject = sixDdFileDeatils.get(i);
					String sixddMode = sixDdFileObject.getMode();
					Long sixddFileId = sixDdFileObject.getN_ID();
					
                    // File Upload
//                    if (!(sixDdFileObject.getFile1() == null)) {
//                        String base64FileData = sixDdFileObject.getFile1();
//                        byte[] decodedFileData = Base64.getDecoder().decode(base64FileData);
//                        String generatedFileName = sixDdFileObject.getV_FILE_1_FILENAME();
//                        String uniqueFileName = UUID.randomUUID().toString() + "_" + generatedFileName;
//						String bucketUrl = "http://tnhb-land-docs.s3-website-us-east-1.amazonaws.com/";
//						String filepath = bucketUrl + uniqueFileName;
//						byte[] fileBytes = decodedFileData;
//						AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAR4WRUXSBWFW47VVO", "iG602vjmGQXcs8CCk2NHyWaVGyfHMcN3eGhVsygl");
//						S3Client s3Client = S3Client.builder()
//								.region(Region.US_EAST_1)
//								.credentialsProvider(() -> credentials)
//								.build();
//						PutObjectRequest request = PutObjectRequest.builder()
//								.bucket("tnhb-land-docs")
//								.key(uniqueFileName)
//								.build();
//						PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
//						sixDdFileObject.setV_FILE_1_FILENAME(generatedFileName);
//                        sixDdFileObject.setV_FILE_1_FILEPATH(filepath);
//                    }

//                    if (!(sixDdFileObject.getFile2() == null)) {
//                        String base64FileData = sixDdFileObject.getFile2();
//                        byte[] decodedFileData = Base64.getDecoder().decode(base64FileData);
//                        String generatedFileName = sixDdFileObject.getV_FILE_2_FILENAME();
//                        String uniqueFileName = UUID.randomUUID().toString() + "_" + generatedFileName;
//						String bucketUrl = "http://tnhb-land-docs.s3-website-us-east-1.amazonaws.com/";
//						String filepath = bucketUrl + uniqueFileName;
//						byte[] fileBytes = decodedFileData;
//						AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAR4WRUXSBWFW47VVO", "iG602vjmGQXcs8CCk2NHyWaVGyfHMcN3eGhVsygl");
//						S3Client s3Client = S3Client.builder()
//								.region(Region.US_EAST_1)
//								.credentialsProvider(() -> credentials)
//								.build();
//						PutObjectRequest request = PutObjectRequest.builder()
//								.bucket("tnhb-land-docs")
//								.key(uniqueFileName)
//								.build();
//						PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
//						sixDdFileObject.setV_FILE_2_FILENAME(generatedFileName);
//                        sixDdFileObject.setV_FILE_2_FILEPATH(filepath);
//                    }
                    
                    // Save Entity
					if (sixddFileId == null && sixddMode.equals("create")) {
                        sixDdFileObject.setN_UNIQUE_ID(createdUniqueCode);
                        SixDdFileEntity savedData = sisDdfileRepo.save(sixDdFileObject);
						createdFileId = savedData.getN_ID();
						createdFileName = savedData.getV_FILE_1_FILENAME();
						} 
					
					// Update Entity
					else if (sixddMode.equals("edit")) {
						sisDdfileRepo.save(sixDdFileObject);
					}

					// Delete
					if (sixddFileId != null && sixddMode.equals("delete")) {
						sisDdfileRepo.deleteById(sixddFileId);
					}

					// !6dd Fourth Tab Dynamic values Save Update Delete
					List<DynamicValueEntity> dynamicValueFourthList = sixDdFileObject.getDynamicValuesDetails();
					if (dynamicValueFourthList != null) {

						for (DynamicValueEntity dynamicValueObj : dynamicValueFourthList) {
							String dynamicvalueMode = dynamicValueObj.getMode();
							Long dynamicvalueId = dynamicValueObj.getN_ID();
							
							// Save Entity
							if (dynamicvalueId == null && dynamicvalueMode.equals("create")) {
								dynamicValueObj.setN_UNIQUE_ID(createdUniqueCode);
								dynamicValueObj.setN_FILE_ID(createdFileId);
								dynamicValueObj.setV_FILE_NAME(createdFileName);
								dynamicValueRepo.save(dynamicValueObj);
								} 
							
							// Update Entity
							else if (dynamicvalueMode.equals("edit")) {
								dynamicValueRepo.save(dynamicValueObj);
							}

							// Delete
							if (dynamicvalueId != null && dynamicvalueMode.equals("delete")) {
								dynamicValueRepo.deleteById(dynamicvalueId);
							}
						}

					} else {

					}

					// !6dd Fourth Tab Dynamic Files Save Update Delete

					List<SixDdDynamicFileEntity> sixDdDynamicFileList = sixDdFileObject
							.getSixDdDynamicFileEntityValuesDetails();

					if (sixDdDynamicFileList != null) {

						for (SixDdDynamicFileEntity sixDdDynamicValueObj : sixDdDynamicFileList) {
							String sixddFileDynamicValueMode = sixDdDynamicValueObj.getMode();
							Long sixddFileDynamicValueId = sixDdDynamicValueObj.getN_ID();

							// Save Entity
							if (sixddFileDynamicValueId == null && sixddFileDynamicValueMode.equals("create")) {
								sixDdDynamicValueObj.setN_UNIQUE_ID(createdUniqueCode);
								sixDdDynamicValueObj.setN_FILE_ID(createdFileId);
								sixDdDynamicFileRepo.save(sixDdDynamicValueObj);
							}

							// Update Entity
							else if (sixddFileDynamicValueMode.equals("edit")) {
								sixDdDynamicFileRepo.save(sixDdDynamicValueObj);
							}

							// Delete
							if (sixddFileDynamicValueId != null && sixddFileDynamicValueMode.equals("delete")) {
								sixDdDynamicFileRepo.deleteById(sixddFileDynamicValueId);
							}
						}

					} else {

					}

				}
			} else {

			}

			// Fifth Tab start here

			List<AwardFileEntity> awardFileDeatils = saveModel.getAwardTabDetails();

			if (awardFileDeatils != null) {

				for (int i = 0; i < awardFileDeatils.size(); i++) {
					AwardFileEntity awardFileObject = awardFileDeatils.get(i);
					String awardMode = awardFileObject.getMode();
					Long awardFileId = awardFileObject.getN_ID();
					
                    // File Upload
//					if (!(awardFileObject.getFile() == null)) {
//                        String base64FileData = awardFileObject.getFile();
//                        byte[] decodedFileData = Base64.getDecoder().decode(base64FileData);
//                        String generatedFileName = awardFileObject.getV_FILE_NAME();
//                        String uniqueFileName = UUID.randomUUID().toString() + "_" + generatedFileName;
//						String bucketUrl = "http://tnhb-land-docs.s3-website-us-east-1.amazonaws.com/";
//						String filepath = bucketUrl + uniqueFileName;
//						byte[] fileBytes = decodedFileData;
//						AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAR4WRUXSBWFW47VVO", "iG602vjmGQXcs8CCk2NHyWaVGyfHMcN3eGhVsygl");
//						S3Client s3Client = S3Client.builder()
//								.region(Region.US_EAST_1)
//								.credentialsProvider(() -> credentials)
//								.build();
//						PutObjectRequest request = PutObjectRequest.builder()
//								.bucket("tnhb-land-docs")
//								.key(uniqueFileName)
//								.build();
//						PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
//						awardFileObject.setV_FILE_NAME(generatedFileName);
//                        awardFileObject.setV_FILE_PATH(filepath);
//                    }
					
                    // Save Entity
					if (awardFileId == null && awardMode.equals("create")) {
						awardFileObject.setN_UNIQUE_ID(createdUniqueCode);
                        AwardFileEntity savedData = awardFileRepo.save(awardFileObject);
						createdFileId = savedData.getN_ID();
						createdFileName = savedData.getV_FILE_NAME();
						} 
					
					// Update Entity
					else if (awardMode.equals("edit")) {
						awardFileRepo.save(awardFileObject);
					}

					// Delete
					if (awardFileId != null &&  awardMode.equals("delete")) {
						awardFileRepo.deleteById(awardFileId);
					}
					

					//Award Fifth Tab Dynamic values Save Update Delete

					List<DynamicValueEntity> dynamicValueFifthList = awardFileObject.getDynamicValuesDetails();

					if (dynamicValueFifthList != null) {

						for (DynamicValueEntity dynamicValueObj : dynamicValueFifthList) {
							String dynamicvalueMode = dynamicValueObj.getMode();
							Long dynamicvalueId = dynamicValueObj.getN_ID();
							
							// Save Entity
							if (dynamicvalueId == null && dynamicvalueMode.equals("create")) {
								dynamicValueObj.setN_UNIQUE_ID(createdUniqueCode);
								dynamicValueObj.setN_FILE_ID(createdFileId);
								dynamicValueObj.setV_FILE_NAME(createdFileName);
								dynamicValueRepo.save(dynamicValueObj);
								} 
							
							// Update Entity
							else if (dynamicvalueMode.equals("edit")) {
								dynamicValueRepo.save(dynamicValueObj);
							}

							// Delete
							if (dynamicvalueId != null &&  dynamicvalueMode.equals("delete")) {
								dynamicValueRepo.deleteById(dynamicvalueId);
							}
						}

					} else {

					}

					//Award Fifth Tab Dynamic Files Save Update Delete

					List<AwardOtherFileEntity> awardOtherFileList = awardFileObject
							.getAwardOtherFileEntityValuesDetails();
					if (awardOtherFileList != null) {
						for (AwardOtherFileEntity awardOtherFileValueObj : awardOtherFileList) {
							String awardFileDynamicValueMode = awardOtherFileValueObj.getMode();
							Long awardFileDynamicValueId = awardOtherFileValueObj.getN_ID();

                            // File Upload
//							if (!(awardOtherFileValueObj.getFile() == null)) {
//                                String base64FileData = awardOtherFileValueObj.getFile();
//                                byte[] decodedFileData = Base64.getDecoder().decode(base64FileData);
//								String fileExtension = getFileExtensionFromSignature(decodedFileData);
//								String generatedFileName = awardOtherFileValueObj.getV_FILE_NAME();
//                                String uniqueFileName = UUID.randomUUID().toString() + "_" + generatedFileName + '.' + fileExtension ;
//								String bucketUrl = "http://tnhb-land-docs.s3-website-us-east-1.amazonaws.com/";
//								String filepath = bucketUrl + uniqueFileName;
//								byte[] fileBytes = decodedFileData;
//								AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAR4WRUXSBWFW47VVO", "iG602vjmGQXcs8CCk2NHyWaVGyfHMcN3eGhVsygl");
//								S3Client s3Client = S3Client.builder()
//										.region(Region.US_EAST_1)
//										.credentialsProvider(() -> credentials)
//										.build();
//								PutObjectRequest request = PutObjectRequest.builder()
//										.bucket("tnhb-land-docs")
//										.key(uniqueFileName)
//										.build();
//								PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
//								awardOtherFileValueObj.setV_FILE_NAME(generatedFileName);
//                                awardOtherFileValueObj.setV_FILE_PATH(filepath);
//                            }

							// Save Entity
							if (awardFileDynamicValueId == null && awardFileDynamicValueMode.equals("create")) {
								awardOtherFileValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardOtherFileValueObj.setN_FILE_ID(createdFileId);
								awardOtherFileRepo.save(awardOtherFileValueObj);
                             } 
							
							// Update Entity
							else if (awardFileDynamicValueMode.equals("edit")) {
								awardOtherFileRepo.save(awardOtherFileValueObj);
							}

							// Delete
							if (awardFileDynamicValueId != null && awardFileDynamicValueMode.equals("delete")) {
								awardOtherFileRepo.deleteById(awardFileDynamicValueId);
							}
						}

					} else {

					}

					// Award Fifth Tab Direct Payment Save Update Delete
					List<AwardDirectPaymentEntity> awardDirectPaymentEntityList = awardFileObject
							.getAwardDirectPaymentEntityValuesDetails();

					if (awardDirectPaymentEntityList != null) {
						for (AwardDirectPaymentEntity awardDirectPaymentValueObj : awardDirectPaymentEntityList) {
							String awardDirectpaymentDynamicValueMode = awardDirectPaymentValueObj.getMode();
							Long awardDirectpaymentDynamicValueId = awardDirectPaymentValueObj.getN_ID();

							// Save Entity
							if (awardDirectpaymentDynamicValueId == null
									&& awardDirectpaymentDynamicValueMode.equals("create")) {
								awardDirectPaymentValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardDirectPaymentValueObj.setN_FILE_ID(createdFileId);
								awardDirectPaymentRepo.save(awardDirectPaymentValueObj);
								}
							
							// Update Entity
							else if (awardDirectpaymentDynamicValueMode.equals("edit")) {
								awardDirectPaymentRepo.save(awardDirectPaymentValueObj);
							}

							// Delete
							if (awardDirectpaymentDynamicValueId != null && awardDirectpaymentDynamicValueMode.equals("delete")) {
								awardDirectPaymentRepo.deleteById(awardDirectpaymentDynamicValueId);
							}
						}

					} else {

					}

					// Award Fifth Tab Revenue Payment Save Update Delete

					List<AwardRevenuePaymentEntity> awardRevenuePaymentEntityList = awardFileObject
							.getAwardRevenuePaymentEntityValuesDetails();
					if (awardRevenuePaymentEntityList != null) {
						for (AwardRevenuePaymentEntity awardRevenuePaymentEntityValueObj : awardRevenuePaymentEntityList) {
							String awardRevenuepaymentDynamicValueMode = awardRevenuePaymentEntityValueObj.getMode();
							Long awardRevenuepaymentDynamicValueId = awardRevenuePaymentEntityValueObj.getN_ID();

							// Save Entity
							if (awardRevenuepaymentDynamicValueId == null
									&& awardRevenuepaymentDynamicValueMode.equals("create")) {
								awardRevenuePaymentEntityValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardRevenuePaymentEntityValueObj.setN_FILE_ID(createdFileId);
								awardRevenuePaymentRepo.save(awardRevenuePaymentEntityValueObj);
								} 
							
							// Update Entity
							else if (awardRevenuepaymentDynamicValueMode.equals("edit")) {
								awardRevenuePaymentRepo.save(awardRevenuePaymentEntityValueObj);
							}

							// Delete
							if (awardRevenuepaymentDynamicValueId != null && awardRevenuepaymentDynamicValueMode.equals("delete")) {
								awardRevenuePaymentRepo.deleteById(awardRevenuepaymentDynamicValueId);
							}
						}

					} else {

					}

					// Award Fifth Tab Court deposit Payment Save Update Delete
					List<AwardCourtDepositPaymentEntity> awardCourtDepositPaymentEntityList = awardFileObject
							.getAwardCourtDepositPaymentEntityValuesDetails();
					if (awardCourtDepositPaymentEntityList != null) {

						for (AwardCourtDepositPaymentEntity awardCourtDepositPaymentEntityValueObj : awardCourtDepositPaymentEntityList) {
							String awardCourtdepositpaymentDynamicValueMode = awardCourtDepositPaymentEntityValueObj
									.getMode();
							Long awardCourtdepositpaymentDynamicValueId = awardCourtDepositPaymentEntityValueObj
									.getN_ID();

							// Save Entity
							if (awardCourtdepositpaymentDynamicValueId == null
									&& awardCourtdepositpaymentDynamicValueMode.equals("create")) {
								awardCourtDepositPaymentEntityValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardCourtDepositPaymentEntityValueObj.setN_FILE_ID(createdFileId);
								awardCourtDepositPaymentRepo.save(awardCourtDepositPaymentEntityValueObj);
								} 
							
							// Update Entity
							else if (awardCourtdepositpaymentDynamicValueMode.equals("edit")) {
								awardCourtDepositPaymentRepo.save(awardCourtDepositPaymentEntityValueObj);
							}

							// Delete
							if (awardCourtdepositpaymentDynamicValueId != null && awardCourtdepositpaymentDynamicValueMode.equals("delete")) {
								awardCourtDepositPaymentRepo.deleteById(awardCourtdepositpaymentDynamicValueId);
							}
						}

					} else {

					}

					// Award Fifth Tab Possession Taken Over Save Update Delete

					List<AwardPossessionTakenOverEntity> awardPossessionTakenOverEntityList = awardFileObject
							.getAwardPossessionTakenOverEntityValuesDetails();
					if (awardPossessionTakenOverEntityList != null) {
						for (AwardPossessionTakenOverEntity awardPossessionTakenOverEntityValueObj : awardPossessionTakenOverEntityList) {
							String awardPossessionTakenOverDynamicValueMode = awardPossessionTakenOverEntityValueObj
									.getMode();
							Long awardPossessionTakenOverDynamicValueId = awardPossessionTakenOverEntityValueObj
									.getN_ID();

							// Save Entity
							if (awardPossessionTakenOverDynamicValueId == null
									&& awardPossessionTakenOverDynamicValueMode.equals("create")) {
								awardPossessionTakenOverEntityValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardPossessionTakenOverEntityValueObj.setN_FILE_ID(createdFileId);
								awardPossessionTakenOverRepo.save(awardPossessionTakenOverEntityValueObj);
								} 
							
							// Update Entity
							else if (awardPossessionTakenOverDynamicValueMode.equals("edit")) {
								awardPossessionTakenOverRepo.save(awardPossessionTakenOverEntityValueObj);
							}

							// Delete
							if (awardPossessionTakenOverDynamicValueId != null && awardPossessionTakenOverDynamicValueMode.equals("delete")) {
								awardPossessionTakenOverRepo.deleteById(awardPossessionTakenOverDynamicValueId);
							}
						}

					} else {

					}

					List<AwardnotUtilisedLhoSelectedExtentList> awardnotUtilisedLhoSelectedExtentList = awardFileObject
							.getAwardnotUtilisedLhoSelectedExtentList();

					if (awardnotUtilisedLhoSelectedExtentList != null) {
						for (AwardnotUtilisedLhoSelectedExtentList awardnotUtilisedLhoSelectedExtentListValueObj : awardnotUtilisedLhoSelectedExtentList) {
							String awardnotUtilisedLhoSelectedExtentDynamicValueMode = awardnotUtilisedLhoSelectedExtentListValueObj
									.getMode();
							Long awardnotUtilisedLhoSelectedExtentDynamicValueId = awardnotUtilisedLhoSelectedExtentListValueObj
									.getN_ID();

							// Save Entity
							if (awardnotUtilisedLhoSelectedExtentDynamicValueId == null
									&& awardnotUtilisedLhoSelectedExtentDynamicValueMode.equals("create")) {
								awardnotUtilisedLhoSelectedExtentListValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardnotUtilisedLhoSelectedExtentListValueObj.setN_FILE_ID(createdFileId);
								awardnotutilisedlhoselectedextentlistrepo.save(awardnotUtilisedLhoSelectedExtentListValueObj);
							}

							// Update Entity
							else if (awardnotUtilisedLhoSelectedExtentDynamicValueMode.equals("edit")) {
								awardnotutilisedlhoselectedextentlistrepo.save(awardnotUtilisedLhoSelectedExtentListValueObj);
							}

							// Delete
							if (awardnotUtilisedLhoSelectedExtentDynamicValueId != null && awardnotUtilisedLhoSelectedExtentDynamicValueMode.equals("delete")) {
								awardnotutilisedlhoselectedextentlistrepo.deleteById(awardnotUtilisedLhoSelectedExtentDynamicValueId);
							}
						}
					} else {

					}


					// Award Fifth Tab Possession Not Taken Over Save Update Delete

					List<AwardPossessionNotTakenOverEntity> awardPossessionNotTakenOverEntityList = awardFileObject
							.getAwardPossessionNotTakenOverEntityValuesDetails();

					if (awardPossessionNotTakenOverEntityList != null) {
						for (AwardPossessionNotTakenOverEntity awardPossessionNotTakenOverEntityValueObj : awardPossessionNotTakenOverEntityList) {
							String awardPossessionNotTakenOverDynamicValueMode = awardPossessionNotTakenOverEntityValueObj
									.getMode();
							Long awardPossessionNotTakenOverDynamicValueId = awardPossessionNotTakenOverEntityValueObj
									.getN_ID();

							// Save Entity
							if (awardPossessionNotTakenOverDynamicValueId == null
									&& awardPossessionNotTakenOverDynamicValueMode.equals("create")) {
								awardPossessionNotTakenOverEntityValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardPossessionNotTakenOverEntityValueObj.setN_FILE_ID(createdFileId);
								awardPossessionNotTakenOverRepo.save(awardPossessionNotTakenOverEntityValueObj);
								} 
							
							// Update Entity
							else if (awardPossessionNotTakenOverDynamicValueMode.equals("edit")) {
								awardPossessionNotTakenOverRepo.save(awardPossessionNotTakenOverEntityValueObj);
							}

							// Delete
							if (awardPossessionNotTakenOverDynamicValueId != null && awardPossessionNotTakenOverDynamicValueMode.equals("delete")) {
								awardPossessionNotTakenOverRepo.deleteById(awardPossessionNotTakenOverDynamicValueId);
							}
						}

					} else {

					}

					List<AwardUtilisedLhoSelectedExtentList> awardUtilisedLhoSelectedExtentList = awardFileObject
							.getAwardUtilisedLhoSelectedExtentList();

					if (awardUtilisedLhoSelectedExtentList != null) {
						for (AwardUtilisedLhoSelectedExtentList awardUtilisedLhoSelectedExtentListValueObj : awardUtilisedLhoSelectedExtentList) {
							String awardUtilisedLhoSelectedExtentDynamicValueMode = awardUtilisedLhoSelectedExtentListValueObj
									.getMode();
							Long awardUtilisedLhoSelectedExtentDynamicValueId = awardUtilisedLhoSelectedExtentListValueObj
									.getN_ID();

							// Save Entity
							if (awardUtilisedLhoSelectedExtentDynamicValueId == null
									&& awardUtilisedLhoSelectedExtentDynamicValueMode.equals("create")) {
								awardUtilisedLhoSelectedExtentListValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardUtilisedLhoSelectedExtentListValueObj.setN_FILE_ID(createdFileId);
								awardutilisedlhoselectedextentlistrepo.save(awardUtilisedLhoSelectedExtentListValueObj);
							}

							// Update Entity
							else if (awardUtilisedLhoSelectedExtentDynamicValueMode.equals("edit")) {
								awardutilisedlhoselectedextentlistrepo.save(awardUtilisedLhoSelectedExtentListValueObj);
							}

							// Delete
							if (awardUtilisedLhoSelectedExtentDynamicValueId != null && awardUtilisedLhoSelectedExtentDynamicValueMode.equals("delete")) {
								awardutilisedlhoselectedextentlistrepo.deleteById(awardUtilisedLhoSelectedExtentDynamicValueId);
							}
						}
					} else {

					}

					// Award Fifth Tab Possession Not Taken Over Save Update Delete

					List<AwardPossessionExtentAvailableEntity> awardPossessionExtentAvailableEntityList = awardFileObject
							.getAwardPossessionExtentAvailableEntityValuesDetails();
					if (awardPossessionExtentAvailableEntityList != null) {
						for (AwardPossessionExtentAvailableEntity awardPossessionExtentAvailableEntityValueObj : awardPossessionExtentAvailableEntityList) {
							String awardPossessionExtentAvailableDynamicValueMode = awardPossessionExtentAvailableEntityValueObj
									.getMode();
							Long awardPossessionExtentAvailableDynamicValueId = awardPossessionExtentAvailableEntityValueObj
									.getN_ID();

							// Save Entity
							if (awardPossessionExtentAvailableDynamicValueId == null
									&& awardPossessionExtentAvailableDynamicValueMode.equals("create")) {
								awardPossessionExtentAvailableEntityValueObj.setN_UNIQUE_ID(createdUniqueCode);
								awardPossessionExtentAvailableEntityValueObj.setN_FILE_ID(createdFileId);
								awardPossessionExtentAvailableRepo.save(awardPossessionExtentAvailableEntityValueObj);
								} 
							
							// Update Entity
							else if (awardPossessionExtentAvailableDynamicValueMode.equals("edit")) {
								awardPossessionExtentAvailableRepo.save(awardPossessionExtentAvailableEntityValueObj);
							}

							// Delete
							if (awardPossessionExtentAvailableDynamicValueId != null && awardPossessionExtentAvailableDynamicValueMode.equals("delete")) {
								awardPossessionExtentAvailableRepo
										.deleteById(awardPossessionExtentAvailableDynamicValueId);
							}
						}

					} else {

					}

				}
			} else {

			}

			// Sixth Tab start here

			LastTabSaveDetails lastTabSaveDetails = saveModel.getLeftoverTabDetails();
			
			if (lastTabSaveDetails != null) {
				
				// Sixth Tab Left over b/w lps and 4(1) Save Update Delete First Tab
				List<LeftOverLPS4OneEntity> leftOverLPS4OneList = lastTabSaveDetails.getLeftOverLPS4OneEntity();
				if (leftOverLPS4OneList != null) {
					for (LeftOverLPS4OneEntity leftOverLPS4OneValueObj : leftOverLPS4OneList) {
						String leftOverLPS4OnevalueMode = leftOverLPS4OneValueObj.getMode();
						Long leftOverLPS4OnevalueId = leftOverLPS4OneValueObj.getPrimarykey();

						//Save and Update 
						if (leftOverLPS4OnevalueMode.equals("edit")) {
							if (leftOverLPS4OneValueObj.getN_UNIQUE_ID() == null) {
								leftOverLPS4OneValueObj.setN_UNIQUE_ID(createdUniqueCode);
								leftOverLPS4OneRepo.save(leftOverLPS4OneValueObj);
							} else {
								leftOverLPS4OneRepo.save(leftOverLPS4OneValueObj);
							}
						}

						// Delete
						if (leftOverLPS4OnevalueId != null && leftOverLPS4OnevalueMode.equals("delete")) {
							leftOverLPS4OneRepo.deleteById(leftOverLPS4OnevalueId);
						}
					}

				} else {

				}

				// ! Sixth Tab Left over b/w 4(1) and 6dd Save Update Delete Second tab

				List<Left4One6DDEntity> left4One6DDEntityList = lastTabSaveDetails.getLeft4One6DDEntity();
				if (left4One6DDEntityList != null) {
					for (Left4One6DDEntity left4One6DDValueObj : left4One6DDEntityList) {

						String left4One6DDvalueMode = left4One6DDValueObj.getMode();
						Long left4One6DDvalueId = left4One6DDValueObj.getPrimarykey();
						
						//Save and Update
							if (left4One6DDvalueMode.equals("edit")) {
								
								if (left4One6DDValueObj.getN_UNIQUE_ID() == null) {
								left4One6DDValueObj.setN_UNIQUE_ID(createdUniqueCode);
								left4One6DDRepo.save(left4One6DDValueObj);
							} 
						    else {
								left4One6DDRepo.save(left4One6DDValueObj);
							}

						}

						// Delete
						if (left4One6DDvalueId != null && left4One6DDvalueMode.equals("delete")) {
							left4One6DDRepo.deleteById(left4One6DDvalueId);
						}
					}

				} else {

				}



				// LPS Second Tab Save Update Delete
				List<LPSFileEntity> lpsFileDeatils = saveModel.getLpsTabDetails();

				if (lpsFileDeatils != null) {
					for (LPSFileEntity lpsObject : lpsFileDeatils) {

						String lpsMode = lpsObject.getMode();
						Long lpsFileId = lpsObject.getN_ID();




						// Save Entity
						if (lpsFileId == null && lpsMode.equals("create")) {
							lpsObject.setN_UNIQUE_ID(createdUniqueCode);
							LPSFileEntity savedData = lPSFileRepo.save(lpsObject);
							createdFileId = savedData.getN_ID();
							createdFileName = savedData.getV_FILE_NAME();
						}

						// Update Entity
						else if (lpsMode.equals("edit")) {
							lPSFileRepo.save(lpsObject);
						}

						// Delete
						if (lpsFileId != null && lpsMode.equals("delete")) {
							lPSFileRepo.deleteById(lpsFileId);
						}

						// LPS Second Tab File Village Save Update Delete
						List<LPSVillageEntity> lpsVillageList = lpsObject.getLpsVillageDetails();

						if (lpsVillageList != null) {

							for (LPSVillageEntity lpsVillageObj : lpsVillageList) {
								String villageMode = lpsVillageObj.getMode();
								Long villageId = lpsVillageObj.getN_ID();

								// Save Entity
								if (villageId == null && villageMode.equals("create")) {
									lpsVillageObj.setN_UNIQUE_ID(createdUniqueCode);
									lpsVillageObj.setN_FILE_ID(createdFileId);
									lPSVillageRepo.save(lpsVillageObj);
								}

								// Update Entity
								else if ( villageMode.equals("edit")) {
									lPSVillageRepo.save(lpsVillageObj);
								}

								// Delete
								if (villageId != null && villageMode.equals("delete")) {
									lPSVillageRepo.deleteById(villageId);
								}
							}

						} else {
						}

						// LPS Second Tab Dynamic values Save Update Delete

						List<DynamicValueEntity> dynamicValueList = lpsObject.getDynamicValuesDetails();

						if (dynamicValueList != null) {

							for (DynamicValueEntity dynamicValueObj : dynamicValueList) {
								String dynamicvalueMode = dynamicValueObj.getMode();
								Long dynamicvalueId = dynamicValueObj.getN_ID();

								// Save Entity
								if (dynamicvalueId == null && dynamicvalueMode.equals("create")) {
									dynamicValueObj.setN_UNIQUE_ID(createdUniqueCode);
									dynamicValueObj.setN_FILE_ID(createdFileId);
									dynamicValueObj.setV_FILE_NAME(createdFileName);
									dynamicValueRepo.save(dynamicValueObj);
								}

								// Update Entity
								else if (dynamicvalueMode.equals("edit")) {
									dynamicValueRepo.save(dynamicValueObj);
								}

								// Delete
								if ( dynamicvalueId != null &&  dynamicvalueMode.equals("delete")) {
									dynamicValueRepo.deleteById(dynamicvalueId);
								}
							}

						} else {

						}

						// LPS Second Tab Dynamic Files Save Update Delete

						List<LPSFileDynamicValueEntity> lPSFileDynamicValueList = lpsObject
								.getLpsFileDynamicValuesDetails();

						if (lPSFileDynamicValueList != null) {

							for (LPSFileDynamicValueEntity lPSFileDynamicValueObj : lPSFileDynamicValueList) {
								String lPSFileDynamicValueMode = lPSFileDynamicValueObj.getMode();
								Long lPSFileDynamicValueId = lPSFileDynamicValueObj.getN_ID();



								// Save Entity
								if (lPSFileDynamicValueId == null && lPSFileDynamicValueMode.equals("create")) {
									lPSFileDynamicValueObj.setN_UNIQUE_ID(createdUniqueCode);
									lPSFileDynamicValueObj.setN_FILE_ID(createdFileId);
									lpsFileDynamicValueRepo.save(lPSFileDynamicValueObj);
								}

								// Update Entity
								else if (lPSFileDynamicValueMode.equals("edit")) {
									lpsFileDynamicValueRepo.save(lPSFileDynamicValueObj);
								}

								// Delete
								if (lPSFileDynamicValueId != null &&  lPSFileDynamicValueMode.equals("delete")) {
									lpsFileDynamicValueRepo.deleteById(lPSFileDynamicValueId);
								}
							}

						} else {

						}

					}
				} else {

				}

				// ! Sixth Tab Left over b/w 6dd and Award Save Update Delete Third tab

				List<Left6DDAwardEntity> left6DDAwardEntityList = lastTabSaveDetails.getLeft6DDAwardEntity();
				if (left6DDAwardEntityList != null) {

					for (Left6DDAwardEntity left6DDAwardValueObj : left6DDAwardEntityList) {

						String left6DDAwardvalueMode = left6DDAwardValueObj.getMode();
						Long left6DDAwardvalueId = left6DDAwardValueObj.getPrimarykey();
						
						// Save and Update Entity
						if (left6DDAwardvalueMode.equals("edit")) {

							if (left6DDAwardValueObj.getN_UNIQUE_ID() == null) {
								left6DDAwardValueObj.setN_UNIQUE_ID(createdUniqueCode);
								left6DDAwardRepo.save(left6DDAwardValueObj);
							} else {
								left6DDAwardRepo.save(left6DDAwardValueObj);
							}
						}

						// Delete
						if (left6DDAwardvalueId != null && left6DDAwardvalueMode.equals("delete")) {
							left6DDAwardRepo.deleteById(left6DDAwardvalueId);
						}
					}

				} else {

				}

			} else {

			}

			return String.valueOf(createdUniqueCode);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//Home Screen List
	@Override
	public List<LandDigitListViewV2> mainView(String types, String values) {
		List<Object[]> identityNoList = landDigitDataDivisionWiseRepo.mainview();

		List<LandDigitListViewV2> resultArray = new ArrayList<>();

		List<String> divisionsToInclude = fetchDivisionsByValue(values);

		for (Object[] listData : identityNoList) {
			LandDigitListViewV2 iteratorObj = new LandDigitListViewV2();
			iteratorObj.setN_UNIQUE_ID(((Integer) listData[0]).longValue());
			iteratorObj.setV_NAME_OF_CIRCLE(listData[1] != null ? listData[1].toString() : null);
			iteratorObj.setV_NAME_OF_DIVISION(listData[2] != null ? listData[2].toString() : null);
			iteratorObj.setV_NAME_OF_DISTRICT(listData[3] != null ? listData[3].toString() : null);
			iteratorObj.setV_NAME_OF_SCHEME(listData[4] != null ? listData[4].toString() : null);
			iteratorObj.setV_NAME_OF_VILLAGE(listData[5] != null ? listData[5].toString() : null);
			iteratorObj.setV_TOTAL_EXTENT(listData[6] != null ? listData[6].toString() : null);
			iteratorObj.setV_PHO_TOTAL_EXTENT(listData[7] != null ? listData[7].toString() : null);
			iteratorObj.setV_PNHO_TOTAL_EXTENT(listData[8] != null ? listData[8].toString() : null);
			iteratorObj.setV_PHO_SCHEME_TOTAL_EXTENT(listData[9] != null ? listData[9].toString() : null);
			iteratorObj.setSixdd_total_extent(listData[10] != null ? listData[10].toString() : null);
			iteratorObj.setFourone_total_extent(listData[11] != null ? listData[11].toString() : null);
			iteratorObj.setAward_court_deposit(listData[12] != null ? listData[12].toString() : null);
			iteratorObj.setAward_direct_payment(listData[13] != null ? listData[13].toString() : null);
			iteratorObj.setAward_revenue_payment(listData[14] != null ? listData[14].toString() : null);

			if ("All".equals(types) ||
					("circle".equals(types) && dataMatchesCircle(iteratorObj, values)) ||
					("division".equals(types) && dataMatchesDivision(iteratorObj, values)) ||
					("chief".equals(types) && divisionsToInclude.contains(iteratorObj.getV_NAME_OF_DIVISION()))) {
				resultArray.add(iteratorObj);
			}
		}
		return resultArray;
	}

	private boolean dataMatchesCircle(LandDigitListViewV2 data, String circle) {
	    return circle.equals(data.getV_NAME_OF_CIRCLE());
	}

	private boolean dataMatchesDivision(LandDigitListViewV2 data, String division) {
	    return division.equals(data.getV_NAME_OF_DIVISION());
	}

	private List<String> fetchDistrictsByValue(String values) {
		return landDigitDataDivisionWiseRepo.getDistrictsByCityOrRural(values);
	}
	
	private List<String> fetchDivisionsByValue(String values) {
	    List<String> divisions = landDigitDataDivisionWiseRepo.getDivisionsByCityOrRural(values);
	    return divisions;
	}
	
	//Report Village Count
//	@Override
//	public List<LandDigitCountVillageViewV2> maincountvillageView(String types, String values) {
//		List<Object[]> identityNoList = landDigitDataDivisionWiseRepo.maincountvillageView();
//
//		List<LandDigitCountVillageViewV2> resultArray = new ArrayList<LandDigitCountVillageViewV2>();
//
//		List<String> divisionsToInclude = fetchDivisionsByValue(values);
//
//		for (Object[] listData : identityNoList) {
//			LandDigitCountVillageViewV2 iteratorObj = new LandDigitCountVillageViewV2();
//
//			iteratorObj.setV_NAME_OF_DIVISION(listData[0] != null ? listData[0].toString() : null);
//			iteratorObj.setV_NAME_OF_CIRCLE(listData[1] != null ? listData[1].toString() : null);
//			iteratorObj.setV_VILLAGE(listData[2] != null ? listData[2].toString() : null);
//			iteratorObj.setLps_file_count(listData[3] != null ? listData[3].toString() : null);
//			iteratorObj.setFour_one_count(listData[4] != null ? listData[4].toString() : null);
//			iteratorObj.setSixdd_file_count(listData[5] != null ? listData[5].toString() : null);
//			iteratorObj.setAward_file_count(listData[6] != null ? listData[6].toString() : null);
//			iteratorObj.setUnique_Id(listData[7] != null ? listData[7].toString() : null);
//
//			String uniqueId = iteratorObj.getUnique_Id();
//
//		// call extend Acres Details
//
//			List<Object[]> acresDetails = landDigitDataDivisionWiseRepo.getExtendAcresDetails(uniqueId);
//
//			if(!acresDetails.isEmpty()) {
//				Object[] acresDetail = acresDetails.get(0);
//
//				iteratorObj.setFourOneAcres((Double) acresDetail[0]);
//				iteratorObj.setSixDDAcres((Double) acresDetail[1]);
//				iteratorObj.setAwardAcres((Double) acresDetail[2]);
//				iteratorObj.setLpsAcres((Double) acresDetail[3]);
//				iteratorObj.setPossissiontakenOver((Double) acresDetail[4]);
//				iteratorObj.setPossiosionNotTakenOver((Double) acresDetail[5]);
//
//			}
//
//
//			if ("All".equals(types) ||
//		            ("circle".equals(types) && dataMatchesCircleCountVillage(iteratorObj, values)) ||
//		            ("division".equals(types) && dataMatchesDivisionCountVillage(iteratorObj, values)) ||
//		            ("chief".equals(types) && divisionsToInclude.contains(iteratorObj.getV_NAME_OF_DIVISION()))) {
//		            resultArray.add(iteratorObj);
//		        }
//		}
//		return resultArray;
//		}
//

	@Override
	public List<LandDigitCountVillageViewV2> maincountvillageView(String types, String values) {
		List<Object[]> identityNoList = landDigitDataDivisionWiseRepo.maincountvillageView();

		List<LandDigitCountVillageViewV2> resultArray = new ArrayList<>();

		List<String> divisionsToInclude = fetchDivisionsByValue(values);
		List<String> districtsToInclude = fetchDistrictsByValue(values);

		for (Object[] listData : identityNoList) {
			LandDigitCountVillageViewV2 iteratorObj = new LandDigitCountVillageViewV2();

			iteratorObj.setV_NAME_OF_DIVISION(listData[0] != null ? listData[0].toString() : null);
			iteratorObj.setV_NAME_OF_CIRCLE(listData[1] != null ? listData[1].toString() : null);
			iteratorObj.setV_NAME_OF_DISTRICT(listData[2] != null ? listData[2].toString() : null);
			iteratorObj.setV_VILLAGE(listData[3] != null ? listData[3].toString() : null);
			iteratorObj.setLps_file_count(listData[4] != null ? listData[4].toString() : null);
			iteratorObj.setFour_one_count(listData[5] != null ? listData[5].toString() : null);
			iteratorObj.setSixdd_file_count(listData[6] != null ? listData[6].toString() : null);
			iteratorObj.setAward_file_count(listData[7] != null ? listData[7].toString() : null);
			iteratorObj.setUnique_Id(listData[8] != null ? listData[8].toString() : null);

			String uniqueId = iteratorObj.getUnique_Id();

			// call extend Acres Details

			List<Object[]> acresDetails = landDigitDataDivisionWiseRepo.getExtendAcresDetails(uniqueId);

			if (!acresDetails.isEmpty()) {
				Object[] acresDetail = acresDetails.get(0);

				iteratorObj.setFourOneAcres((Double) acresDetail[0]);
				iteratorObj.setSixDDAcres((Double) acresDetail[1]);
				iteratorObj.setAwardAcres((Double) acresDetail[2]);
				iteratorObj.setLpsAcres((Double) acresDetail[3]);
				iteratorObj.setPossissiontakenOver((Double) acresDetail[4]);
				iteratorObj.setPossiosionNotTakenOver((Double) acresDetail[5]);
			}

			// Here you can access the district directly from the iteratorObj
			// without needing to pass it as a parameter
			if ("All".equals(types) ||
					("circle".equals(types) && dataMatchesCircleCountVillage(iteratorObj, values)) ||
					("division".equals(types) && dataMatchesDivisionCountVillage(iteratorObj, values)) ||
					("district".equals(types) && districtsToInclude.contains(iteratorObj.getV_NAME_OF_DISTRICT())) ||
					("chief".equals(types) && divisionsToInclude.contains(iteratorObj.getV_NAME_OF_DIVISION()))) {
				resultArray.add(iteratorObj);
			}
		}
		return resultArray;
	}
	private boolean dataMatchesCircleCountVillage(LandDigitCountVillageViewV2 data, String circle) {
	    return circle.equals(data.getV_NAME_OF_CIRCLE());
	}

	private boolean dataMatchesDivisionCountVillage(LandDigitCountVillageViewV2 data, String division) {
	    return division.equals(data.getV_NAME_OF_DIVISION());
	}

	//Report Village List
	@Override
	public List<LandDigitListVillageViewV2> mainlistvillageView(String types, String values) {
		List<Object[]> identityNoList = landDigitDataDivisionWiseRepo.mainlistvillageView();

		List<LandDigitListVillageViewV2> resultArray = new ArrayList<LandDigitListVillageViewV2>();

		for (Object[] listData : identityNoList) {
			LandDigitListVillageViewV2 iteratorObj = new LandDigitListVillageViewV2();
			
			List<String> divisionsToInclude = fetchDivisionsByValue(values);

			iteratorObj.setN_UNIQUE_ID(listData[0] != null ? listData[0].toString() : null);
			iteratorObj.setV_NAME_OF_DIVISION(listData[1] != null ? listData[1].toString() : null);
			iteratorObj.setV_NAME_OF_CIRCLE(listData[2] != null ? listData[2].toString() : null);
			iteratorObj.setV_NAME_OF_SCHEME(listData[3] != null ? listData[3].toString() : null);
			iteratorObj.setLps_village(listData[4] != null ? listData[4].toString() : null);
			iteratorObj.setFour_one_village(listData[5] != null ? listData[5].toString() : null);
			iteratorObj.setSixdd_village(listData[6] != null ? listData[6].toString() : null);
			iteratorObj.setAward_village(listData[7] != null ? listData[7].toString() : null);
			iteratorObj.setV_NAME_OF_DISTRICT(listData[8] !=null ?listData[8].toString():null);
			if ("All".equals(types) ||
		            ("circle".equals(types) && dataMatchesCircleListVillage(iteratorObj, values)) ||
		            ("division".equals(types) && dataMatchesDivisionListVillage(iteratorObj, values))
		            || ("chief".equals(types) && divisionsToInclude.contains(iteratorObj.getV_NAME_OF_DIVISION()))){
		            resultArray.add(iteratorObj);
		        }
		}
		return resultArray;
		}
	
	private boolean dataMatchesCircleListVillage(LandDigitListVillageViewV2 data, String circle) {
	    return circle.equals(data.getV_NAME_OF_CIRCLE());
	}

	private boolean dataMatchesDivisionListVillage(LandDigitListVillageViewV2 data, String division) {
	    return division.equals(data.getV_NAME_OF_DIVISION());
	}

	
	
	//Delete
	@Override
	public void deleteV2LandDetailsById(Long id) {
		landDigitv2MainRepo.deleteById(id);
	}

	@Transient
	@Override
	public List<AwardFileEntity> getAllAwardFiles() {
		return awardFileRepo.findAll();
	}


	@Transient
	@Override
	public List<Map<String, Object>> getAwardFileWithTotals() {
		List<Object[]> resultList = awardFileRepo.findAwardFileWithTotals();
		List<Map<String, Object>> resultMapList = new ArrayList<>();

		for (Object[] row : resultList) {
			Map<String, Object> rowMap = new HashMap<>();
			rowMap.put("N_UNIQUE_ID", row[0]);
			rowMap.put("V_AWARD_NO", row[1]);
			rowMap.put("D_AWARD_DATE", row[2]);
			rowMap.put("V_FILE_NAME", row[3]);
			rowMap.put("V_FILE_PATH", row[4]);
			rowMap.put("N_TOTAL_AWARD_AMOUNT", row[5]);
			rowMap.put("V_TOTAL_EXTENT", row[6]);
			rowMap.put("N_ID", row[7]);
			rowMap.put("V_PHO_TOTAL_EXTENT", row[8]);
			rowMap.put("V_PNHO_TOTAL_EXTENT", row[9]);
			rowMap.put("V_PHO_SCHEME_TOTAL_EXTENT", row[10]);
			rowMap.put("totalAmountDirectPayment", row[11]);
			rowMap.put("totalAmountCourtDeposit", row[12]);
			rowMap.put("totalAmountRevenuePayment", row[13]);

			resultMapList.add(rowMap);
		}

		return resultMapList;
	}




	//Home Screen count
	@Override
	public List<CountEntityV2> getviewcount(String types, String values) {
		List<CountEntityV2> mainViewList = null;
		if (types.equals("All")) {
			mainViewList = countEntityV2Repo.findAll();
		} else if (types.equals("circle")) {
			mainViewList = countEntityV2Repo.findByCircle(values);
		} else if (types.equals("chief")) {
			mainViewList = countEntityV2Repo.findByCityNrual(values);
		} else if (types.equals("division")) {
			mainViewList = countEntityV2Repo.findByDivision(values);
			}
		return mainViewList;
	}


	//Get Image
	@Override
	public Optional<LandDigitMediaEntity> getImages(Long id) {

		Optional<LandDigitMediaEntity> value = landDigitMediaRepo.findById(id);

		return value;

	}
	
	
	//Save Image
	@Override
	public String saveImagesAndVideos(MultipartFile[] files, String N_UNIQUE_ID) throws IOException {


		try {

			for (MultipartFile multipartFile : files) {
				LandDigitMediaEntity mediaEntity = new LandDigitMediaEntity();
				mediaEntity.setFileName(multipartFile.getOriginalFilename());
				mediaEntity.setMediaData(multipartFile.getBytes());
				mediaEntity.setFileType(multipartFile.getContentType());
				mediaEntity.setN_UNIQUE_ID(Long.parseLong(N_UNIQUE_ID));
				LandDigitMediaEntity savedMedia = landDigitMediaRepo.save(mediaEntity);
			}
			return "Uploaded Succesfully";

		} catch (Exception e) {
			e.printStackTrace();
			}
		return null;
	}




	@Override
	public AwsConfig getAwsConfig() {
		AwsConfig awsConfig = new AwsConfig();
		awsConfig.setAccessKeyId(accessKeyId);
		awsConfig.setSecretKey(secretKey);
		awsConfig.setRegion(region);
		awsConfig.setBucketName(bucketName);
		return awsConfig;
	}

	@Override
	public List<DivisionList> findByDivisionAndDistrictAndVillage(String division, String district, String village) {
		return divisionlist.selectByDivisionAndDistrictAndVillage(division, district, village);
	}

	@Override
	public List<DivisionList> findByCircleAndDistrictAndVillage(String circle, String district, String village) {
		return divisionlist.selectByCircleAndDistrictAndVillage(circle, district, village);
	}


	@Override
	public List<DivisionList> findByCircleAndDivisionAndDistrict(String circle, String division, String district) {
		return divisionlist.selectCircleAndDivisionAndDistrict(circle, division, district);
	}





}


