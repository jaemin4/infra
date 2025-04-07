package com.v01.event.interfaces.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqPaymentHistoryDto {
    private Long userId;
    private Long amount;
    private String reason;
}
