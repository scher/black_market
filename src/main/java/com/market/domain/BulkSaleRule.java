package com.market.domain;

import com.market.repository.Repository;

public class BulkSaleRule extends AbstractPricingRule {
    private int numOfItems;
    private double salePrice;

    public BulkSaleRule(String sku, Repository repository, int numOfItems, double salePrice) {
        super(sku, repository);
        this.numOfItems = numOfItems;
        this.salePrice = salePrice;
    }

    @Override
    protected double calculateDiscount(Cart cart) {
        long skuItemsInCart = cart.getItems().stream().filter(item -> item.equals(sku)).count();
        Item item = repository.getItem(sku);
        return skuItemsInCart * item.getPrice() - skuItemsInCart * salePrice;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        long skuItemsInCart = cart.getItems().stream().filter(item -> item.equals(sku)).count();
        return skuItemsInCart > numOfItems;
    }
}
