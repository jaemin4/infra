package com.v01.event.domain.product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findByStockId(Long stockId);

    Product decreaseStock(Product product);
}
