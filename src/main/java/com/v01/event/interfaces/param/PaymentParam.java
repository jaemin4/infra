package com.v01.event.interfaces.param;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentParam {
    private Long userId;
    private Long orderId;
    private int amount;
}
