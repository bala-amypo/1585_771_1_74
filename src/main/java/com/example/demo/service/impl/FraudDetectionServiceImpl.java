package com.example.demo.service.impl;

import com.example.demo.model.Claim;
import com.example.demo.model.FraudCheckResult;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.service.FraudDetectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudCheckResultRepository resultRepository;
    private final FraudRuleRepository fraudRuleRepository;


   public FraudDetectionServiceImpl(ClaimRepository claimRepository,
                                 FraudRuleRepository fraudRuleRepository,
                                 FraudCheckResultRepository resultRepository) {
    this.claimRepository = claimRepository;
    this.fraudRuleRepository = fraudRuleRepository;
    this.resultRepository = resultRepository;
}


    @Override
    public FraudCheckResult processFraudCheck(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);
        result.setCheckedAt(LocalDateTime.now());

        if (claim.getSuspectedRules() != null &&
            !claim.getSuspectedRules().isEmpty()) {

            result.setIsFraudulent(true);
            String rules = claim.getSuspectedRules()
                    .stream()
                    .map(r -> r.getRuleName())
                    .collect(Collectors.joining(", "));
            result.setMatchedRules(rules);

        } else {
            result.setIsFraudulent(false);
            result.setMatchedRules("");
        }

        return resultRepository.save(result);
    }

    @Override
    public java.util.List<FraudCheckResult> getChecksByClaimId(Long claimId) {
        return resultRepository.findByClaimId(claimId);
    }
}
