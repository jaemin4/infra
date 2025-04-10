package com.v01.event.interfaces.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ResFetchProductDto {
    private String productName;
    private Long productPrice;
    private Long productQuantity;
}
