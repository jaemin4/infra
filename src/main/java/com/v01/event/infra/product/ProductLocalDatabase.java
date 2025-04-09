package com.v01.event.infra.product;

import com.v01.event.domain.product.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductLocalDatabase {

    private final Map<Long, Product> localDb = new ConcurrentHashMap<>(1);
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        // 기본 상품 등록: 컴퓨터(id=1, 가격=1_000_000원, 수량=10개)
        Product defaultProduct = new Product(1L, "컴퓨터", 1_000_000L, 10L);
        localDb.put(defaultProduct.getProductId(), defaultProduct);
    }


    public Optional<Product> findByStockId(Long stockId) {
        return Optional.ofNullable(localDb.get(stockId));
    }

    public Product decreaseStock(Product product) {
        Product existing = localDb.get(product.getProductId());
        if (existing == null) {
            throw new RuntimeException("Product with ID " + product.getProductId() + " not found in localDb.");
        } else{
            existing.setProductQuantity(product.getProductQuantity());
            return existing;
        }

    }




}
