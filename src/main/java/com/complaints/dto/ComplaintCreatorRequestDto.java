package com.complaints.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ComplaintCreatorRequestDto {
    @NotNull(message = "productId is mandatory")
    Long productId;
    @NotBlank(message = "reporterName is mandatory")
    String reporterName;
    @NotBlank(message = "message is mandatory")
    String message;
}
