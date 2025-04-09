package com.v01.event.domain.balance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    private Long balanceId;
    private Long userId;
    private Long amount;

    public Balance(Long userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }


}
