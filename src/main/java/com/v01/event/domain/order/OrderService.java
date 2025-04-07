package com.v01.event.domain.order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void markOrderAsPaid(Long orderId) {
        System.out.println("[Order] 주문 결제 완료 처리: orderId=" + orderId);
    }
}
