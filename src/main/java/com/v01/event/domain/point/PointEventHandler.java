package com.v01.event.domain.point;

import com.v01.event.domain.payment.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointEventHandler {

    private final PointService pointService;

    @EventListener
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        pointService.usePoints(event.getUserId(), event.getAmount());
    }

}
