package com.market;

import com.market.calculation.Checkout;
import com.market.domain.BulkSaleRule;
import com.market.domain.ByMorePayLessRule;
import com.market.domain.FreeBundleRule;
import com.market.domain.PricingRule;
import com.market.repository.InMemoryRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) throws IOException {
        ArrayList<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new ByMorePayLessRule("atv", InMemoryRepository.getInstance(), 3, 2));
        pricingRules.add(new BulkSaleRule("ipd", InMemoryRepository.getInstance(), 4, 499.99));
        pricingRules.add(new FreeBundleRule("mbp", InMemoryRepository.getInstance(), "vga"));
        Checkout checkout = new Checkout(pricingRules);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Input SKU separated by commas: ");
        String input = reader.readLine();
        String[] splited = input.split(",");
        for (String sku : splited) {
            checkout.scan(sku.trim());
        }
        System.out.println("Total: " + checkout.total());
    }
}
