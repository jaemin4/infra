package com.v01.event.interfaces.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResCompleteChargeDto {
    Long userId;
    Long requestBalance;
    Long totalBalance;
}
