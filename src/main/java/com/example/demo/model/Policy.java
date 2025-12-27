package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;

    private String policyType;

    private LocalDate startDate;

    private LocalDate endDate;

    /* -------------------------------
       Relationship with User
    -------------------------------- */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /* -------------------------------
       Relationship with Claims
    -------------------------------- */
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private Set<Claim> claims = new HashSet<>();

    /* -------------------------------
       Constructors
    -------------------------------- */

    // ✅ Required by JPA
    public Policy() {
    }

    // ✅ REQUIRED BY TEST CASES
    public Policy(User user,
                  String policyNumber,
                  String policyType,
                  LocalDate startDate,
                  LocalDate endDate) {
        this.user = user;
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /* -------------------------------
       Getters & Setters
    -------------------------------- */

    // ✅ REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Set<Claim> claims) {
        this.claims = claims;
    }
}
