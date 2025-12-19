package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "fraud_rules")
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ruleName;

    private int severity; // FIX: should be int, NOT String

    @ManyToMany(mappedBy = "suspectedRules")
    private Set<Claim> claims;

    public FraudRule() {}

    // FIX: add constructor the tests expect
    public FraudRule(String ruleName, int severity) {
        this.ruleName = ruleName;
        this.severity = severity;
    }

    // getters + setters
    public Long getId() { return id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public int getSeverity() { return severity; }
    public void setSeverity(int severity) { this.severity = severity; }

    public Set<Claim> getClaims() { return claims; }
    public void setClaims(Set<Claim> claims) { this.claims = claims; }
}
