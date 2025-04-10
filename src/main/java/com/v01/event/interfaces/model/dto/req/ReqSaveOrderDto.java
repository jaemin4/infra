package com.v01.event.interfaces.model.dto.req;

import com.v01.event.interfaces.model.param.OrderItemParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqSaveOrderDto {
    private Long userId;
    private List<OrderItemParam> items;
    private Long totalAmount;
}
