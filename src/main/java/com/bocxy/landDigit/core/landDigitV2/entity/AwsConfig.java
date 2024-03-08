package com.bocxy.landDigit.core.landDigitV2.entity;


import lombok.Data;

@Data
public class AwsConfig {

    private String accessKeyId;
    private String secretKey;
    private String region;
    private String bucketName;

    // Constructor, getters, and setters
}
