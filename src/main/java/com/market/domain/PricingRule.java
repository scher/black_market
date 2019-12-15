package com.market.domain;

public interface PricingRule {
    boolean isApplicable();

    void applyRule(Cart cart);
}
