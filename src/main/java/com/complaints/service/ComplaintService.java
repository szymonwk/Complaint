package com.complaints.service;

import com.complaints.component.CountryIpExtractor;
import com.complaints.component.DateCreator;
import com.complaints.dto.ComplaintCreatorRequestDto;
import com.complaints.dto.ComplaintDto;
import com.complaints.dto.ComplaintUpdateDto;
import com.complaints.exception.MissingComplaintException;
import com.complaints.repository.ComplaintsRepository;
import com.complaints.repository.entity.ComplaintEntity;
import com.complaints.repository.entity.key.ComplaintId;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintsRepository complaintsRepository;
    private final CountryIpExtractor countryIpExtractor;
    private final DateCreator dateCreator;

    public void createComplaint(ComplaintCreatorRequestDto complaintCreatorRequestDto, HttpServletRequest httpServletRequest) {
        Optional<ComplaintEntity> complaint = complaintsRepository.findById(new ComplaintId(complaintCreatorRequestDto.getProductId(), complaintCreatorRequestDto.getReporterName()));
        ComplaintEntity complaintEntity;
        if (complaint.isPresent()) {
            complaintEntity = complaint.get();
            complaintEntity.setCounter(complaintEntity.getCounter() + 1);
        } else {
            complaintEntity = new ComplaintEntity(new ComplaintId(complaintCreatorRequestDto.getProductId(), complaintCreatorRequestDto.getReporterName()),
                    complaintCreatorRequestDto.getMessage(), dateCreator.getCurrentInstantDate(), countryIpExtractor.extractCountryIp(httpServletRequest.getRemoteAddr()), 1);
        }
        complaintsRepository.save(complaintEntity);
    }

    public void updateComplaint(ComplaintUpdateDto complaintDto, String reporterName, int productId) {
        ComplaintEntity complaint = complaintsRepository.findById(new ComplaintId(productId, reporterName)).orElseThrow(MissingComplaintException::new);
        complaint.setMessage(complaintDto.getMessage());
        complaintsRepository.save(complaint);
    }

    public List<ComplaintDto> getComplaints() {
        List<ComplaintEntity> complaintEntities = (List<ComplaintEntity>) complaintsRepository.findAll();
        return complaintEntities.parallelStream().map(complaintEntity -> new ComplaintDto(complaintEntity.getComplaintId().getProductId(), complaintEntity.getComplaintId().getReporterName(), complaintEntity.getMessage(), complaintEntity.getCreatedDate(), complaintEntity.getCountry(), complaintEntity.getCounter())).collect(Collectors.toList());
    }
}
