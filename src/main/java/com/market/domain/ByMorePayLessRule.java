package com.market.domain;

import com.market.repository.Repository;

public class ByMorePayLessRule extends AbstractPricingRule {
    private String sku;
    private int itemsToBy;
    private int itemsToPay;
    private Repository repository;

    public ByMorePayLessRule(String sku, int itemsToBy, int itemsToPay, Repository repository) {
        this.sku = sku;
        this.itemsToBy = itemsToBy;
        this.itemsToPay = itemsToPay;
        this.repository = repository;
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
