package com.v01.event.interfaces.model.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UseBalanceParam {
    private Long userId;
    private Long amount;

}
