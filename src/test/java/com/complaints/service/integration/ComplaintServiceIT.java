package com.complaints.service.integration;

import com.complaints.repository.ComplaintsRepository;
import com.complaints.repository.entity.ComplaintEntity;
import com.complaints.repository.entity.key.ComplaintId;
import com.complaints.service.integration.config.BaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;

public class ComplaintServiceIT extends BaseConfig {
    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    private ComplaintsRepository complaintsRepository;

    @Test
    void shouldReturnAllComplaints() {
        //given
        complaintsRepository.save(new ComplaintEntity(new ComplaintId(1, "A"), "Message", Instant.now(), "POLAND", 1));

        //when//then
        webTestClient
                .get()
                .uri("/complaints")
                .exchange()
                .expectBody()
                .jsonPath("$.[0].productId").isEqualTo(1)
                .jsonPath("$.[0].reporterName").isEqualTo("A")
                .jsonPath("$.[0].message").isEqualTo("Message")
                .jsonPath("$.[0].country").isEqualTo("POLAND")
                .jsonPath("$.[0].counter").isEqualTo(1);
    }
}