package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "fraud_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ruleName;

    @NotBlank
    private String conditionField;

    @NotBlank
    private String operator;

    @NotBlank
    private String value;

    @NotBlank
    private String severity;

    @ManyToMany(mappedBy = "suspectedRules")
    private Set<Claim> claims = new HashSet<>();

    public FraudRule() {}

    // constructor with generic params & empty body as per documentation
    public FraudRule(String a, String b, String c,
                     String d, String e) {
    }

    public Long getId() { return id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getConditionField() { return conditionField; }
    public void setConditionField(String conditionField) {
        this.conditionField = conditionField;
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() { return value; }
    public void setValue(String value) {
        this.value = value;
    }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Set<Claim> getClaims() { return claims; }
}
