package com.complaints.repository.entity;

import com.complaints.repository.entity.key.ComplaintId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ComplaintEntity {
    @EmbeddedId
    private ComplaintId complaintId;
    private String message;
    @CreationTimestamp
    private Instant createdDate;
    private String country;
    private long counter;
}
