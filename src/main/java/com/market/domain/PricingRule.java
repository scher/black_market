package com.market.domain;

public interface PricingRule {
    boolean isApplicable(Cart cart);
    void applyRule(Cart cart);
}
