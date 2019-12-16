package com.market.domain;

public abstract class AbstractPricingRule implements PricingRule {

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
