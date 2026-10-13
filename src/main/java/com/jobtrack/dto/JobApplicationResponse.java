package com.jobtrack.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jobtrack.entity.ApplicationStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Setter
@Getter
@AllArgsConstructor 
public class JobApplicationResponse {
    private Long id;
    private String company;
    private String jobTitle;
    private String jobUrl;
    private String location;
    private BigDecimal salary;
    private LocalDate appliedDate;
    private ApplicationStatus status;
    private String notes;
    private LocalDate createdAt;
}
