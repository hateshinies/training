package com.greatestsasha.training.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
    String id;
    String objectName;
    String bucket;
}