package com.market.domain;

import com.market.repository.Repository;

public class FreeBundleRule extends AbstractPricingRule {
    String skuToBundle;

    public FreeBundleRule(String sku, Repository repository, String skuToBundle) {
        super(sku, repository);
        this.skuToBundle = skuToBundle;
    }

    @Override
    protected double calculateDiscount(Cart cart) {
        Item item = repository.getItem(skuToBundle);
        if (cart.getItems().stream().anyMatch(i -> i.equals(skuToBundle))) {
            return item.getPrice();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getItems().stream().anyMatch(item -> item.equals(sku));
    }
}
