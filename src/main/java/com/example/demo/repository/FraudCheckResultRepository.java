package com.example.demo.repository;

import com.example.demo.model.FraudCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudCheckResultRepository
        extends JpaRepository<FraudCheckResult, Long> {

    List<FraudCheckResult> findByClaimId(Long claimId);
}
