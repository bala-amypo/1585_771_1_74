package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class FraudRuleServiceImpl implements FraudRuleService {

    private static final Set<String> ALLOWED_SEVERITIES =
            Set.of("LOW", "MEDIUM", "HIGH");

    private final FraudRuleRepository fraudRuleRepository;

    public FraudRuleServiceImpl(FraudRuleRepository fraudRuleRepository) {
        this.fraudRuleRepository = fraudRuleRepository;
    }

    @Override
    public FraudRule addRule(FraudRule rule) {

        if (!ALLOWED_SEVERITIES.contains(rule.getSeverity())) {
            throw new IllegalArgumentException("Invalid severity level");
        }

        fraudRuleRepository.findByRuleName(rule.getRuleName())
                .ifPresent(r -> {
                    throw new IllegalArgumentException("Rule name already exists");
                });

        return fraudRuleRepository.save(rule);
    }

    @Override
    public List<FraudRule> getAllRules() {
        return fraudRuleRepository.findAll();
    }
}
