package com.market.calculation;

import com.market.domain.Cart;
import com.market.domain.PricingRule;
import com.market.repository.InMemoryRepository;
import com.market.repository.Repository;

import java.util.List;

public class Checkout {
    private List<PricingRule> pricingRules;
    private Cart cart = new Cart();
    private Repository repository = InMemoryRepository.getInstance();

    public Checkout(List<PricingRule> pricingRules) {
        this.pricingRules = pricingRules;

    }

    public void scan(String sku) {
        cart.addItem(sku);
    }

    public double total() {
        double totalWithoutDeals = calculateTotalWithoutDeals();
        cart.setTotal(totalWithoutDeals);
        for (PricingRule pricingRule : pricingRules) {
            if (pricingRule.isApplicable(cart)) {
                pricingRule.applyRule(cart);
            }
        }
        return cart.getTotal();
    }

    private double calculateTotalWithoutDeals() {
        int sum = 0;
        for (String sku : cart.getItems()) {
            sum += repository.getItem(sku).getPrice();
        }
        return sum;
    }
}
