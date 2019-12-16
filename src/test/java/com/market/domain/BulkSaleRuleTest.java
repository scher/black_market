package com.market.domain;

import com.market.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class BulkSaleRuleTest {

    public static final String SKU = "sku";

    @Test
    void applyRule() {
        Item item1 = new Item(SKU, "dummy", 100);
        Repository repository = mock(Repository.class);
        when(repository.getItem(SKU)).thenReturn(item1);
        BulkSaleRule classToTest = new BulkSaleRule(SKU, repository, 2, 90);
        Cart cartMock = mock(Cart.class);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item1);
        items.add(item1);
        when(cartMock.getItems()).thenReturn(items);
        when(cartMock.getTotal()).thenReturn(300.00);

        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertTrue(applicable);
        classToTest.applyRule(cartMock);

        verify(cartMock).setTotal(270);
    }

    @Test
    void applicableFalse() {
        Item item1 = new Item(SKU, "dummy", 100);
        Repository repository = mock(Repository.class);
        BulkSaleRule classToTest = new BulkSaleRule(SKU, repository, 5, 50);
        Cart cartMock = mock(Cart.class);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertFalse(applicable);
    }

    @Test
    void applicableTrue() {
        Item item1 = new Item(SKU, "dummy", 100);
        Repository repository = mock(Repository.class);
        BulkSaleRule classToTest = new BulkSaleRule(SKU, repository, 1, 50);
        Cart cartMock = mock(Cart.class);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item1);
        items.add(item1);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertTrue(applicable);
    }
}