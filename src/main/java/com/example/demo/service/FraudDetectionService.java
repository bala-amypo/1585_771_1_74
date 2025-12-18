package com.example.demo.service;

import com.example.demo.model.FraudCheckResult;

import java.util.List;

public interface FraudDetectionService {

    FraudCheckResult processFraudCheck(Long claimId);

    List<FraudCheckResult> getChecksByClaimId(Long claimId);
}
