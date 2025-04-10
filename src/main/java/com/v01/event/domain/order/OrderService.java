package com.v01.event.domain.order;

import com.v01.event.interfaces.model.dto.req.ReqSaveOrderDto;
import com.v01.event.interfaces.model.param.OrderParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(ReqSaveOrderDto DTO) {
        Order order = new Order();
        List<OrderItem> items = DTO.getItems().stream()
                .map(p -> new OrderItem(p.getProductId(), p.getQuantity()))
                .toList();

            order.setUserId(DTO.getUserId());
            order.setStatus("CREATED");
            order.setItems(items);
            order.setCreateAt(LocalDateTime.now());
            order.setTotalAmount(DTO.getTotalAmount());

        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }




}
