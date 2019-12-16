package com.market.domain;

import com.market.repository.Repository;

public class BuyMorePayLessRule extends AbstractPricingRule {
    private int itemsToBy;
    private int itemsToPay;

    public BuyMorePayLessRule(String sku, Repository repository, int itemsToBy, int itemsToPay) {
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
        long skuItemsInCart = cart.getItems().stream().filter(item -> item.equals(sku)).count();
        return skuItemsInCart >= itemsToBy;
    }
}
