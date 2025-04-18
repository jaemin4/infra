package com.v01.event.domain.product;

import com.v01.event.interfaces.model.dto.req.ReqCalculateTotalAmountDto;
import com.v01.event.interfaces.model.dto.req.ReqDecreaseStockDto;
import com.v01.event.interfaces.model.param.ReqValidateNoDuplicateItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product decreaseAbleStock(ReqDecreaseStockDto DTO) {
        Product product = productRepository.findByProductId(DTO.getProductId()).orElseThrow(
                () -> new RuntimeException("Product not found : "+ DTO.getProductId())
        );

        if(product.getProductQuantity() < DTO.getQuantity()){
            throw new RuntimeException("재고가 부족합니다.");
        }

        product.setProductQuantity(product.getProductQuantity() - DTO.getQuantity());
        return productRepository.decreaseStock(product);
    }

    public void validateNoDuplicateProducts(List<ReqValidateNoDuplicateItemsDto> DTO) {
        Set<Long> productIds = new HashSet<>();

        for (ReqValidateNoDuplicateItemsDto product : DTO) {
            if (!productIds.add(product.getProductId())) {
                throw new RuntimeException("중복된 상품 ID가 포함되어 있습니다: " + product.getProductId());
            }
        }
    }

    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId).orElseThrow(
                () -> new RuntimeException("Product not found : "+ productId)
        );
    }

    public long calculateTotalAmount(List<ReqCalculateTotalAmountDto> items) {
        return items.stream()
                .mapToLong(item -> {
                    Product product = productRepository.findByProductId(item.getProductId()).orElseThrow(
                            ()->new RuntimeException("Product not found : "+ item.getProductId()));
                    return product.getProductPrice() * item.getQuantity();
                })
                .sum();
    }
}
