package com.market.repository;

import com.market.domain.Item;

public interface Repository {
    Item getItem(String sku);
}
