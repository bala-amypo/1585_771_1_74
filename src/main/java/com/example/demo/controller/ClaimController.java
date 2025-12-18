package com.example.demo.controller;

import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import com.example.demo.util.HqlQueryHelper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService service;
    private final HqlQueryHelper hqlQueryHelper;

    public ClaimController(ClaimService service,
                           HqlQueryHelper hqlQueryHelper) {
        this.service = service;
        this.hqlQueryHelper = hqlQueryHelper;
    }

    @PostMapping
    public Claim createClaim(@RequestParam Long policyId,
                             @Valid @RequestBody Claim claim) {
        return service.createClaim(policyId, claim);
    }

    @GetMapping("/{id}")
    public Claim getClaim(@PathVariable Long id) {
        return service.getClaim(id);
    }

    @GetMapping("/high-value")
    public List<Claim> getHighValueClaims(@RequestParam double minAmount) {
        return hqlQueryHelper.findHighValueClaims(minAmount);
    }

    @GetMapping("/search")
    public List<Claim> searchClaims(@RequestParam String keyword) {
        return hqlQueryHelper.findClaimsByDescriptionKeyword(keyword);
    }
}
