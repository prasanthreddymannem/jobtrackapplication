package com.jobtrack.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobtrack.dto.JobApplicationRequest;
import com.jobtrack.dto.JobApplicationResponse;
import com.jobtrack.entity.JobApplication;
import com.jobtrack.entity.User;
import com.jobtrack.repo.JobApplicationRepo;
import com.jobtrack.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class JobApplicationService {
    private final JobApplicationRepo jobApplicationRepo;
    private final UserRepo userRepo;

    public void createApplication(JobApplicationRequest jobApplicationRequest){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.findByEmail(email)
        .orElseThrow(()->new UsernameNotFoundException("User not found"));
        JobApplication application=new JobApplication();
        application.setCompany(jobApplicationRequest.getCompany());
        application.setJobTitle(jobApplicationRequest.getJobTitle());
        application.setJobUrl(jobApplicationRequest.getJobUrl());
        application.setLocation(jobApplicationRequest.getLocation());
        application.setSalary(jobApplicationRequest.getSalary());
        application.setAppliedDate(jobApplicationRequest.getAppliedDate());
        application.setStatus(jobApplicationRequest.getStatus());
        application.setNotes(jobApplicationRequest.getNotes());

        application.setUser(user);
        jobApplicationRepo.save(application);
    }

    public List<JobApplicationResponse> getMyApplications(){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->
    new UsernameNotFoundException("User not Found"));

    return jobApplicationRepo.findByUserId(user.getId())
        .stream()
        .map(application -> new JobApplicationResponse(
                application.getId(),
                application.getCompany(),
                application.getJobTitle(),
                application.getJobUrl(),
                application.getLocation(),
                application.getSalary(),
                application.getAppliedDate(),
                application.getStatus(),
                application.getNotes(),
                application.getCreatedAt().toLocalDate()
        ))
        .toList();
    }

    public JobApplicationResponse getApplication(Long id){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->
    new UsernameNotFoundException("User not found"));
    JobApplication application=jobApplicationRepo.findByIdAndUserId(id, user.getId())
    .orElseThrow(()->new RuntimeException("Application not found"));
        return new JobApplicationResponse(
                application.getId(),
                application.getCompany(),
                application.getJobTitle(),
                application.getJobUrl(),
                application.getLocation(),
                application.getSalary(),
                application.getAppliedDate(),
                application.getStatus(),
                application.getNotes(),
                application.getCreatedAt().toLocalDate()
        );
    }
     public JobApplicationResponse updateApplication(Long id,JobApplicationRequest request){

        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        JobApplication application=jobApplicationRepo.findByIdAndUserId(id, user.getId())
        .orElseThrow(()->new RuntimeException("Application not found"));
        application.setCompany(request.getCompany());
        application.setJobTitle(request.getJobTitle());
        application.setJobUrl(request.getJobUrl());
        application.setLocation(request.getLocation());
        application.setSalary(request.getSalary());
        application.setAppliedDate(request.getAppliedDate());
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());

        JobApplication updatedApplication =
                jobApplicationRepo.save(application);

         return new JobApplicationResponse(
                updatedApplication.getId(),
                updatedApplication.getCompany(),
                updatedApplication.getJobTitle(),
                updatedApplication.getJobUrl(),
                updatedApplication.getLocation(),
                updatedApplication.getSalary(),
                updatedApplication.getAppliedDate(),
                updatedApplication.getStatus(),
                updatedApplication.getNotes(),
                updatedApplication.getCreatedAt().toLocalDate()
        );
     }

     public void deleteApplication(Long id){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        JobApplication application=jobApplicationRepo.findByIdAndUserId(id, user.getId())
        .orElseThrow(()->new RuntimeException("Application not found"));
        jobApplicationRepo.delete(application);
     }
}
