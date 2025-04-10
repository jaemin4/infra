package com.v01.event.interfaces.model.param;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemParam {
    @NotNull
    @Positive
    private Long productId;

    @NotNull
    @Positive
    private Long quantity;
}
