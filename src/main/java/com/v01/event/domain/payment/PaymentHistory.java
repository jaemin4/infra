package com.v01.event.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory {
    private Long paymentHistoryId;
    private Long userId;
    private Long amount;
    private String reason;

    public PaymentHistory(Long userId, Long amount, String reason) {
        this.userId = userId;
        this.amount = amount;
        this.reason = reason;
    }
}
