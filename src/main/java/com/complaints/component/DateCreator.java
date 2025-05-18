package com.complaints.component;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DateCreator {
    public Instant getCurrentInstantDate() {
        return Instant.now();
    }
}
