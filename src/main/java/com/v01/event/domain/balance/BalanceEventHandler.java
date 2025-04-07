package com.v01.event.domain.balance;

import com.v01.event.domain.payment.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceEventHandler {
    private final BalanceService balanceService;


    @EventListener
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        balanceService.decreaseBalance(event.getUserId(), event.getAmount());
    }
}
