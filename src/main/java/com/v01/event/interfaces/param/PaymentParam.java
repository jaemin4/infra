package com.v01.event.interfaces.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentParam {
    private Long userId;
    private Long productId;
    private Long quantity;
}
