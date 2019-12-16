package com.market.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items = new ArrayList<>();
    private double total;

    public List<Item> getItems() {
        return items;
    }

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
