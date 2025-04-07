package com.v01.event.domain.balance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceHistory {

    private Long balanceHistoryId;
    private Long userId;
    private Long amount;

    public BalanceHistory(Long userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }

}
