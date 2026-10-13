package com.jobtrack.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.dto.JobApplicationRequest;
import com.jobtrack.dto.JobApplicationResponse;
import com.jobtrack.entity.JobApplication;
import com.jobtrack.service.JobApplicationService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController 
@RequestMapping ("/applications")
@RequiredArgsConstructor 
public class JobApplicationController {
    private final JobApplicationService jobApplication;
    @PostMapping
    public ResponseEntity<Void> createApplication(@RequestBody JobApplicationRequest jobApplicationRequest) {
        jobApplication.createApplication(jobApplicationRequest);

        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<JobApplicationResponse> getMyApplications() {
        return jobApplication.getMyApplications();
    }

    @GetMapping("/{id}")
    public JobApplicationResponse getApplication(@PathVariable Long id)  {
        return jobApplication.getApplication(id);
    }

    @PutMapping("/{id}")
    public JobApplicationResponse updateApplication(@PathVariable Long id, @RequestBody JobApplicationRequest request) {
        return jobApplication.updateApplication(id, request);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id){
        jobApplication.deleteApplication(id);
        return  ResponseEntity.noContent().build();
    }
    
}
