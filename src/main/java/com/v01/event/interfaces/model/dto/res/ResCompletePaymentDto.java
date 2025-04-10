package com.v01.event.interfaces.model.dto.res;

import com.v01.event.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResCompletePaymentDto {
    private List<Product> products;
    private Long productPrice;
}
