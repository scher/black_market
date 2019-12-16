package com.market.domain;

import com.market.repository.Repository;

public abstract class AbstractPricingRule implements PricingRule {
    protected String sku;
    protected Repository repository;

    public AbstractPricingRule(String sku, Repository repository) {
        this.sku = sku;
        this.repository = repository;
    }

    @Override
    public void applyRule(Cart cart) {
        double discount = calculateDiscount(cart);
        applyDiscount(discount, cart);
    }

    abstract protected double calculateDiscount(Cart cart);

    private void applyDiscount(double discount, Cart cart) {
        cart.setTotal(cart.getTotal() - discount);
    }
}
