package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fraud_rules")
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ruleName;

    // TEST expects severity as INT (NOT String)
    private int severity;

    @ManyToMany(mappedBy = "suspectedRules")
    private Set<Claim> claims = new HashSet<>();

    public FraudRule() {
        // default no-args constructor (tests use this too)
    }

    // 🔥 TEST EXPECTS EXACTLY THIS CONSTRUCTOR
    public FraudRule(Long id, String ruleName, int severity) {
        this.id=id;
        this.ruleName = ruleName;
        this.severity = severity;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getSeverity() {
        return severity;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    // SETTERS
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }

    // OPTIONAL helper for tests
    public void addClaim(Claim claim) {
        this.claims.add(claim);
    }
}
