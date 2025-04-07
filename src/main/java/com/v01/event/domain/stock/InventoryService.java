package com.v01.event.domain.stock;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public void decreaseStock(Long orderId) {
        System.out.println("[Inventory] 재고 차감: orderId=" + orderId);
    }
}
