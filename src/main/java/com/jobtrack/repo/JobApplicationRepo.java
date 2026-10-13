package com.jobtrack.repo;

import com.jobtrack.entity.JobApplication;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepo extends JpaRepository<JobApplication,Long> {
    List<JobApplication> findByUserId(Long userId);
    Optional<JobApplication> findByIdAndUserId(Long id,Long userId);
}
