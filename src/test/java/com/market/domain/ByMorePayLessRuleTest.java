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
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, 3, 1, repository);
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

        verify(cartMock).setTotal(100);
    }

    @Test
    void ruleApplicableFalse() {
        Item item1 = new Item(SKU, "dummy", 100);
        Repository repository = mock(Repository.class);
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, 5, 3, repository);
        Cart cart = new Cart();
        cart.addItem(item1);
        boolean applicable = classToTest.isApplicable(cart);
        Assertions.assertFalse(applicable);
    }

    @Test
    void ruleApplicableTrue() {
        Item item1 = new Item(SKU, "dummy", 100);
        Repository repository = mock(Repository.class);
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, 2, 1, repository);
        Cart cart = new Cart();
        cart.addItem(item1);
        cart.addItem(item1);
        boolean applicable = classToTest.isApplicable(cart);
        Assertions.assertTrue(applicable);
    }

    @Test
    void ruleApplicableTrueWhenMore() {
        Item item1 = new Item(SKU, "dummy", 100);
        Repository repository = mock(Repository.class);
        ByMorePayLessRule classToTest = new ByMorePayLessRule(SKU, 2, 1, repository);
        Cart cart = new Cart();
        cart.addItem(item1);
        cart.addItem(item1);
        cart.addItem(item1);
        cart.addItem(item1);
        boolean applicable = classToTest.isApplicable(cart);
        Assertions.assertTrue(applicable);
    }
}