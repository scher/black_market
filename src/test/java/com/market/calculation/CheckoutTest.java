package com.market.calculation;

import com.market.domain.PricingRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

class CheckoutTest {

    @Test
    void totalWithoutDeals() {
        Checkout checkout = new Checkout(new ArrayList<>());
        checkout.scan("ipd");
        checkout.scan("ipd");
        double total = checkout.total();
        Assertions.assertEquals(200, total);
    }

    @Test
    void totalWithDeals() {
        PricingRule ruleMock1 = mock(PricingRule.class);
        PricingRule ruleMock2 = mock(PricingRule.class);
        PricingRule ruleMock3 = mock(PricingRule.class);
        when(ruleMock1.isApplicable(anyObject())).thenReturn(true);
        when(ruleMock2.isApplicable(anyObject())).thenReturn(true);
        when(ruleMock3.isApplicable(anyObject())).thenReturn(false);

        ArrayList<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(ruleMock1);
        pricingRules.add(ruleMock2);
        pricingRules.add(ruleMock3);

        Checkout checkout = new Checkout(pricingRules);

        checkout.scan("ipd");
        checkout.scan("ipd");
        double total = checkout.total();
        Assertions.assertEquals(200, total);

        verify(ruleMock1).applyRule(anyObject());
        verify(ruleMock2).applyRule(anyObject());
        verify(ruleMock3, times(0)).applyRule(anyObject());
    }
}