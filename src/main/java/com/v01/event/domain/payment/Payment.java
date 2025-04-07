package com.v01.event.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long id;
    private Long userId;
    private Long orderId;
    private int amount;

    public Payment(Long userId, Long orderId, int amount) {
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
    }

}
