package com.v01.event.interfaces.model.param;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentParam {

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long orderId;

}
