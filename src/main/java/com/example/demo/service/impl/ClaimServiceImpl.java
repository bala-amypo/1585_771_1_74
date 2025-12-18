package com.example.demo.service.impl;

import com.example.demo.model.Claim;
import com.example.demo.model.Policy;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.PolicyRepository;
import com.example.demo.service.ClaimService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository,
                            PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    @Override
    public Claim createClaim(Long policyId, Claim claim) {

        if (claim.getClaimAmount() <= 0) {
            throw new IllegalArgumentException("Claim amount must be positive");
        }

        if (claim.getClaimDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Claim date cannot be in the future");
        }

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        claim.setPolicy(policy);
        return claimRepository.save(claim);
    }

    @Override
    public Claim getClaim(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}
