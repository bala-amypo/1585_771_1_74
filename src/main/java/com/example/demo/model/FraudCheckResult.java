package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_check_results")
public class FraudCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "claim_id")
    private Claim claim;

    @Column(nullable = false)
    private Boolean isFraudulent;

    @Column(length = 2000)
    private String matchedRules;

    @Column(nullable = false)
    private LocalDateTime checkedAt;

    public FraudCheckResult() {}

    public Long getId() { return id; }

    public Claim getClaim() { return claim; }
    public void setClaim(Claim claim) { this.claim = claim; }

    public Boolean getIsFraudulent() { return isFraudulent; }
    public void setIsFraudulent(Boolean isFraudulent) {
        this.isFraudulent = isFraudulent;
    }

    public String getMatchedRules() { return matchedRules; }
    public void setMatchedRules(String matchedRules) {
        this.matchedRules = matchedRules;
    }

    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
