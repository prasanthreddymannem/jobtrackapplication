package com.jobtrack.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jobtrack.entity.ApplicationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class JobApplicationRequest {
    private String company;
    private String jobTitle;
    private String jobUrl;
    private String location;
    private BigDecimal salary;
    private LocalDate appliedDate;
    private ApplicationStatus status;
    private String notes;
}
