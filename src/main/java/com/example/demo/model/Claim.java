package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @NotNull
    private LocalDate claimDate;

    @NotNull
    @Positive
    private Double claimAmount;

    @NotBlank
    private String description;

    @ManyToMany
    @JoinTable(
            name = "claim_fraud_rules",
            joinColumns = @JoinColumn(name = "claim_id"),
            inverseJoinColumns = @JoinColumn(name = "fraud_rule_id")
    )
    private Set<FraudRule> suspectedRules = new HashSet<>();

    public Claim() {}

    // Full constructor with proper assignments
    public Claim(Policy policy, LocalDate claimDate,
                 double claimAmount, String description) {
        this.policy = policy;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.description = description;
    }

    // GETTERS
    public Long getId() { return id; }

    public Policy getPolicy() { return policy; }
    public LocalDate getClaimDate() { return claimDate; }
    public Double getClaimAmount() { return claimAmount; }
    public String getDescription() { return description; }
    public Set<FraudRule> getSuspectedRules() { return suspectedRules; }

    // SETTERS
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuspectedRules(Set<FraudRule> suspectedRules) {
        this.suspectedRules = suspectedRules;
    }

    // HELPER METHODS (useful for service layer)
    public void addSuspectedRule(FraudRule rule) {
        this.suspectedRules.add(rule);
    }

    public void removeSuspectedRule(FraudRule rule) {
        this.suspectedRules.remove(rule);
    }
}
