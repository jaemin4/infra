package com.v01.event.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCompletedEvent {
    private Long userId;
    private Long orderId;
    private int amount;
}
