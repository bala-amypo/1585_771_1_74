package com.example.demo.controller;

import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping("/user/{userId}")
    public Policy createPolicy(
            @PathVariable Long userId,
            @RequestBody Policy policy) {
        return policyService.createPolicy(userId, policy);
    }

    @GetMapping("/user/{userId}")
    public List<Policy> getPolicies(@PathVariable Long userId) {
        return policyService.getPoliciesByUser(userId);
    }
}
