package com.v01.event.domain.order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> getOrderById(Long orderId);

}
