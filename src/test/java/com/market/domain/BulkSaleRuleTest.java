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
        BulkSaleRule classToTest = new BulkSaleRule(SKU, 2, 90, repository);
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
        BulkSaleRule classToTest = new BulkSaleRule(SKU, 5, 50, repository);
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
        BulkSaleRule classToTest = new BulkSaleRule(SKU, 1, 50, repository);
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