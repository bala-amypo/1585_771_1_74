package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_check_results")
public class FraudCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Claim claim;

    private Boolean isFraudulent;
    private String matchedRules;
    private LocalDateTime checkedAt;

    // getters and setters
}
