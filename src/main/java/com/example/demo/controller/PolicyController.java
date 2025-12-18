package com.example.demo.controller;

import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService service;

    public PolicyController(PolicyService service) {
        this.service = service;
    }

    @PostMapping
    public Policy createPolicy(@RequestParam Long userId,
                               @Valid @RequestBody Policy policy) {
        return service.createPolicy(userId, policy);
    }

    @GetMapping("/user/{userId}")
    public List<Policy> getPoliciesByUser(@PathVariable Long userId) {
        return service.getPoliciesByUser(userId);
    }
}
