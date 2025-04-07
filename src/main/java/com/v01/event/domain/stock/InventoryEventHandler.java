package com.v01.event.domain.stock;

import com.v01.event.domain.payment.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventHandler {
    private final InventoryService inventoryService;


    @EventListener
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        inventoryService.decreaseStock(event.getOrderId());
    }
}
