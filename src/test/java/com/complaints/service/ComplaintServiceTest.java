package com.complaints.service;

import com.complaints.component.CountryIpExtractor;
import com.complaints.component.TimeHolder;
import com.complaints.dto.ComplaintDto;
import com.complaints.repository.ComplaintsRepository;
import com.complaints.repository.entity.ComplaintEntity;
import com.complaints.repository.entity.key.ComplaintId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ComplaintServiceTest {
    @Mock
    private ComplaintsRepository complaintsRepository;

    @Mock
    private CountryIpExtractor countryIpExtractor;

    @Mock
    private TimeHolder timeHolder;

    @Test
    void shouldListComplaints() {
        //given
        ComplaintEntity complaintEntity = new ComplaintEntity(new ComplaintId(1L, "A"), "message", Instant.now(), "POLAND", 1L);
        ComplaintEntity complaintEntity1 = new ComplaintEntity(new ComplaintId(2L, "B"), "message", Instant.now(), "POLAND", 1L);
        when(complaintsRepository.findAll()).thenReturn(Arrays.asList(complaintEntity, complaintEntity1));

        //when
        List<ComplaintDto> complaintService = new ComplaintService(complaintsRepository, countryIpExtractor, timeHolder).getComplaints();

        //then
        assertEquals(2, complaintService.size());
    }
}