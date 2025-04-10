package com.v01.event.domain.product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findByProductId(Long stockId);

    Product decreaseStock(Product product);

}
