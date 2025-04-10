package com.v01.event.interfaces.model.param;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentParam {

    @NotNull
    @Positive
    private Long userId;

    @Valid
    @NotNull
    @Size(min = 1, message = "최소 1개 이상의 상품이 필요합니다.")
    private List<ReqPayProduct> payProductList;
}
