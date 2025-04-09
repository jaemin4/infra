package com.v01.event.domain.product;

import com.v01.event.interfaces.dto.req.ReqDecreaseStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product decreaseAbleStock(ReqDecreaseStockDto DTO) {
        Product product = productRepository.findByStockId(DTO.getProductId()).orElseThrow(
                ()-> new RuntimeException("Product not found")
        );

        if(product.getProductQuantity() < 1){
            throw new RuntimeException("재고가 부족합니다.");
        }

        product.setProductQuantity(product.getProductQuantity() - 1);
        return productRepository.decreaseStock(product);
    }
}
