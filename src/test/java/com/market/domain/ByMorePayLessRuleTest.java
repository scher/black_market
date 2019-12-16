package com.market.domain;

import com.market.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class ByMorePayLessRuleTest {

    public static final String SKU = "sku";

    @Test
    void applyRule() {
        Item item1 = new Item(SKU, "dummy", 100);
        Repository repository = mock(Repository.class);
        when(repository.getItem(SKU)).thenReturn(item1);
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, repository, 3, 1);
        Cart cartMock = mock(Cart.class);
        ArrayList<String> items = new ArrayList<>();
        items.add(SKU);
        items.add(SKU);
        items.add(SKU);
        when(cartMock.getItems()).thenReturn(items);
        when(cartMock.getTotal()).thenReturn(300.00);

        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertTrue(applicable);
        classToTest.applyRule(cartMock);

        verify(cartMock).setTotal(100);
    }

    @Test
    void ruleApplicableFalse() {
        Repository repository = mock(Repository.class);
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, repository, 5, 3);
        Cart cartMock = mock(Cart.class);
        ArrayList<String> items = new ArrayList<>();
        items.add(SKU);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertFalse(applicable);
    }

    @Test
    void ruleApplicableTrue() {
        Repository repository = mock(Repository.class);
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, repository, 2, 1);
        Cart cartMock = mock(Cart.class);
        ArrayList<String> items = new ArrayList<>();
        items.add(SKU);
        items.add(SKU);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertTrue(applicable);
    }

    @Test
    void ruleApplicableTrueWhenMore() {
        Repository repository = mock(Repository.class);
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, repository, 2, 1);
        Cart cartMock = mock(Cart.class);
        ArrayList<String> items = new ArrayList<>();
        items.add(SKU);
        items.add(SKU);
        items.add(SKU);
        items.add(SKU);
        when(cartMock.getItems()).thenReturn(items);
        boolean applicable = classToTest.isApplicable(cartMock);
        Assertions.assertTrue(applicable);
    }
}