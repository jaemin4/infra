package com.v01.event.domain.stock;

public interface ProductRepository {

    Product findByStockId(Long stockId);
}
