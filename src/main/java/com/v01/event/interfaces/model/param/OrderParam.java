package com.v01.event.interfaces.model.param;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderParam {

    @NotNull
    @Positive
    private Long userId;

    @Valid
    @NotNull
    @Size(min = 1, message = "최소 1개 이상의 상품이 필요합니다.")
    private List<OrderItemParam> items;


}
