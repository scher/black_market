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
        Item itemToBundle = new Item(SKU_TO_BUNDLE, "another one", 50);
        Repository repository = mock(Repository.class);
        when(repository.getItem(SKU_TO_BUNDLE)).thenReturn(itemToBundle);
        FreeBundleRule classToTest = new FreeBundleRule(SKU, repository, SKU_TO_BUNDLE);
        Cart cartMock = mock(Cart.class);
        ArrayList<String> items = new ArrayList<>();
        items.add(SKU);
        items.add(SKU_TO_BUNDLE);
        when(cartMock.getItems()).thenReturn(items);
        when(cartMock.getTotal()).thenReturn(150.00);

        classToTest.applyRule(cartMock);
        verify(cartMock).setTotal(100);
    }

    @Test
    void applicableTrue() {
        Repository repository = mock(Repository.class);
        FreeBundleRule classToTest = new FreeBundleRule(SKU, repository, SKU_TO_BUNDLE);
        Cart cartMock = mock(Cart.class);
        ArrayList<String> items = new ArrayList<>();
        items.add(SKU);
        items.add(SKU_TO_BUNDLE);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertTrue(applicable);
    }

    @Test
    void applicableFalse() {
        Repository repository = mock(Repository.class);
        FreeBundleRule classToTest = new FreeBundleRule(SKU, repository, SKU_TO_BUNDLE);
        Cart cartMock = mock(Cart.class);
        ArrayList<String> items = new ArrayList<>();
        items.add(SKU_TO_BUNDLE);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertFalse(applicable);
    }
}