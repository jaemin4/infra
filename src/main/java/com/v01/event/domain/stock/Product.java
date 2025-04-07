package com.v01.event.domain.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long productId;
    private String productName;
    private Long productPrice;
    private Long productQuantity;

    public void decrease(Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("감소 수량은 0보다 커야 합니다.");
        }
        if (this.productQuantity < amount) {
            throw new IllegalStateException("재고 부족: 현재 수량 = " + this.productQuantity);
        }
        this.productQuantity -= amount;
    }

    public void increase(Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("증가 수량은 0보다 커야 합니다.");
        }
        this.productQuantity += amount;
    }
}
