package com.v01.event.infra.product;

import com.v01.event.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductLocalDatabase {

    private final Map<Long, Product> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Optional<Product> findByProductId(Long productId) {
        if (productId == null) return Optional.empty();

        return localDb.values().stream()
                .filter(product -> productId.equals(product.getProductId()))
                .findFirst();
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

    public void save(Product product) {
        Long id = idGenerator.getAndIncrement();
        product.setProductId(id);
        localDb.put(id, product);
    }

    public void deleteById(Long productId) {
        localDb.remove(productId);
    }
}
