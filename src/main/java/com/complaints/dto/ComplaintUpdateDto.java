package com.complaints.dto;

import lombok.Value;

import java.time.Instant;

@Value
public class ComplaintUpdateDto {
    String message;
    Instant createdDate;
}
