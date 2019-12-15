package com.market.repository;

import com.market.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileRepositoryFactoryTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void initRepository() {
        Item expectedItemIpd = new Item("ipd", "Super iPad", 549.99);
        Item expectedItemMbp = new Item("mbp", "MacBook Pro", 1399.99);
        FileRepositoryFactory classToTest = new FileRepositoryFactory();
        Repository repository = classToTest.initRepository();
        Item itemIpd = repository.getItem("ipd");
        Item itemMbp = repository.getItem("mbp");
        Assertions.assertEquals(expectedItemIpd, itemIpd);
        Assertions.assertEquals(expectedItemMbp, itemMbp);
    }
}