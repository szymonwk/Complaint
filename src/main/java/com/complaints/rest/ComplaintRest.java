package com.complaints.rest;

import com.complaints.dto.ComplaintCreatorRequestDto;
import com.complaints.dto.ComplaintDto;
import com.complaints.dto.ComplaintUpdateDto;
import com.complaints.service.ComplaintService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintRest {
    private final ComplaintService complaintService;

    @PostMapping
    public void createComplaint(@Valid @RequestBody ComplaintCreatorRequestDto complaintCreatorRequestDto, HttpServletRequest httpServletRequest) {
        complaintService.createComplaint(complaintCreatorRequestDto, httpServletRequest);
    }

    @PatchMapping("/{reporterName}/{productId}")
    public void updateComplaint(@RequestBody ComplaintUpdateDto complaintDto, @PathVariable String reporterName, @PathVariable Long productId) {
        complaintService.updateComplaint(complaintDto, reporterName, productId);
    }

    @GetMapping
    public List<ComplaintDto> getComplaints() {
        return complaintService.getComplaints();
    }
}
