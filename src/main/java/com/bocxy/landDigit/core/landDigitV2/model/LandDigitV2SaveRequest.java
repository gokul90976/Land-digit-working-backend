package com.bocxy.landDigit.core.landDigitV2.model;

import lombok.Data;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LandDigitV2SaveRequest {
    private List<MultipartFile> fouronefile1;
    private List<MultipartFile> fouronefile2;
    private List<MultipartFile> fouronedynamicfile;
    private List<MultipartFile> sixddfile1;
    private List<MultipartFile> sixddfile2;
    private List<MultipartFile> awardfile;
}
