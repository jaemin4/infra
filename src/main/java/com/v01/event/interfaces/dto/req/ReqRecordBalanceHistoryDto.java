package com.v01.event.interfaces.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqRecordBalanceHistoryDto {
    private Long userId;
    private Long amount;


}
