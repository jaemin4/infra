package com.v01.event.infra.stock;

import com.v01.event.domain.stock.Product;
import com.v01.event.domain.stock.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductLocalDatabase productLocalDatabase;

    @Override
    public Product findByStockId(Long stockId) {
        return null;
    }
}
