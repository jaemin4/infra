package com.v01.event.interfaces.model.dto.req;

import com.v01.event.support.enums.BalanceReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqSavePaymentDto {

    private Long userId;
    private Long amount;
    private String reason;


}
