package com.market.domain;

import com.market.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class FreeBundleRuleTest {
    public static final String SKU = "sku";
    public static final String SKU_TO_BUNDLE = "skuToBundle";

    @Test
    void applyRule() {
        Item item1 = new Item(SKU, "dummy", 100);
        Item itemToBundle = new Item(SKU_TO_BUNDLE, "another one", 50);
        Repository repository = mock(Repository.class);
        when(repository.getItem(SKU_TO_BUNDLE)).thenReturn(itemToBundle);
        FreeBundleRule classToTest = new FreeBundleRule(SKU, repository, SKU_TO_BUNDLE);
        Cart cartMock = mock(Cart.class);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(itemToBundle);
        when(cartMock.getItems()).thenReturn(items);
        when(cartMock.getTotal()).thenReturn(150.00);

        classToTest.applyRule(cartMock);
        verify(cartMock).setTotal(100);
    }

    @Test
    void applicableTrue() {
        Item item1 = new Item(SKU, "dummy", 100);
        Item itemToBundle = new Item(SKU_TO_BUNDLE, "another one", 50);
        Repository repository = mock(Repository.class);
        FreeBundleRule classToTest = new FreeBundleRule(SKU, repository, SKU_TO_BUNDLE);
        Cart cartMock = mock(Cart.class);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(itemToBundle);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertTrue(applicable);
    }

    @Test
    void applicableFalse() {
        Item itemToBundle = new Item(SKU_TO_BUNDLE, "another one", 50);
        Repository repository = mock(Repository.class);
        FreeBundleRule classToTest = new FreeBundleRule(SKU, repository, SKU_TO_BUNDLE);
        Cart cartMock = mock(Cart.class);
        ArrayList<Item> items = new ArrayList<>();
        items.add(itemToBundle);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertFalse(applicable);
    }
}