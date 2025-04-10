package com.v01.event.infra.order;

import com.v01.event.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class OrderLocalDatabase {

    private final Map<Long, Order> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Order save(Order order) {
        Long id = idGenerator.getAndIncrement();
        order.setOrderId(id);
        localDb.put(id,order);
        return localDb.get(id);
    }

    public Optional<Order> getOrderById(Long orderId) {
        return Optional.ofNullable(localDb.get(orderId));
    }



}
