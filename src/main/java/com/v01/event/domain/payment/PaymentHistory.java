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
    private Long orderId;
    private String transactionId;
    private String status;

    public PaymentHistory(Long userId, Long amount, Long orderId, String transactionId, String status) {
        this.userId = userId;
        this.amount = amount;
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.status = status;
    }


}
