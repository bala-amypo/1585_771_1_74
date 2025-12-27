package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fraud_check_results")
public class FraudCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isFraudulent;

    private String triggeredRuleName;

    private String rejectionReason;

    // 🔥 REQUIRED BY TESTS
    private String matchedRules;

    // 🔥 REQUIRED BY FraudDetectionServiceImpl
    @OneToOne
    @JoinColumn(name = "claim_id")
    private Claim claim;

    public FraudCheckResult() {
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public boolean isFraudulent() {
        return isFraudulent;
    }

    public void setIsFraudulent(boolean isFraudulent) {
        this.isFraudulent = isFraudulent;
    }

    public String getTriggeredRuleName() {
        return triggeredRuleName;
    }

    public void setTriggeredRuleName(String triggeredRuleName) {
        this.triggeredRuleName = triggeredRuleName;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    // 🔥 TEST SUPPORT
    public String getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(String matchedRules) {
        this.matchedRules = matchedRules;
    }

    // 🔥 SERVICE SUPPORT
    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
}
