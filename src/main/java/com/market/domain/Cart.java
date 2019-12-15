package com.market.domain;

import java.util.List;

public class Cart {
    List<Item> items;
    double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    void addItem(Item item) {
        items.add(item);
    }


}
