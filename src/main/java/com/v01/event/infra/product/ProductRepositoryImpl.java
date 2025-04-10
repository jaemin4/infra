package com.v01.event.infra.product;

import com.v01.event.domain.product.Product;
import com.v01.event.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductLocalDatabase productLocalDatabase;

    @Override
    public Optional<Product> findByStockId(Long stockId) {
        return productLocalDatabase.findByProductId(stockId);
    }

    @Override
    public Product decreaseStock(Product product) {
        return productLocalDatabase.decreaseStock(product);
    }
}
