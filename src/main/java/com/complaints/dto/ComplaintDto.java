package com.complaints.dto;

import lombok.Value;

import java.time.Instant;

@Value
public class ComplaintDto {
    Long productId;
    String reporterName;
    String message;
    Instant createdDate;
    String country;
    long counter;
}
