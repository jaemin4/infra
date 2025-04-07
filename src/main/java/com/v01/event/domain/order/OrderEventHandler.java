package com.v01.event.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

    private final OrderService orderService;

    @EventListener
    public void handlePaymentCompleted(final PaymentCompletedEvent event) {
        orderService.markOrderAsPaid(event.getOrderId());
    }
}
