package com.market.domain;

import java.util.Objects;

public class Item {
    String sku;
    String name;
    double price;

    public Item(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 &&
                sku.equals(item.sku) &&
                name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, price);
    }
}
