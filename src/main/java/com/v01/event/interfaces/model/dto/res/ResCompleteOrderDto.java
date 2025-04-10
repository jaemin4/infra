package com.v01.event.interfaces.model.dto.res;

import com.v01.event.domain.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResCompleteOrderDto {
    private Long orderId;
    private Long totalAmount;
    private String status;
    private List<OrderItem> items;
}
