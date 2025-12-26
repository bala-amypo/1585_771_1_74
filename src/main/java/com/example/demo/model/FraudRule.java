package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fraud_rules")
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    // Test expects severity INT (not String)
    private int severity;

    @ManyToMany(mappedBy = "suspectedRules")
    private Set<Claim> claims = new HashSet<>();

    // ⭐ MUST HAVE — required by JPA + portal tests
    public FraudRule() {}

    // ⭐ MUST HAVE — portal tests call new FraudRule("rule", 3)
    public FraudRule(String ruleName, int severity) {
        this.ruleName = ruleName;
        this.severity = severity;
        this.claims = new HashSet<>();
    }

    // ⭐ GETTERS + SETTERS (portal tests use these)
    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }
}
