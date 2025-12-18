package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @NotBlank
    private String policyNumber;

    @NotBlank
    private String policyType;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    // getters and setters
}
