package com.market.domain;

import com.market.repository.Repository;

public class ByMorePayLessRule extends AbstractPricingRule {
    private int itemsToBy;
    private int itemsToPay;

    public ByMorePayLessRule(String sku, int itemsToBy, int itemsToPay, Repository repository) {
        super(sku, repository);
        this.itemsToBy = itemsToBy;
        this.itemsToPay = itemsToPay;
    }

    @Override
    protected double calculateDiscount(Cart cart) {
        Item item = repository.getItem(sku);
        double price = item.getPrice();
        return price * itemsToBy - price * itemsToPay;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        long skuItemsInCart = cart.getItems().stream().filter(item -> item.getSku().equals(sku)).count();
        return skuItemsInCart >= itemsToBy;
    }
}
