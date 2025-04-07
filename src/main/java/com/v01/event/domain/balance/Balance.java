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

    public void decrease(Long amount){
        if (amount == null || amount <= 0) {
            throw new RuntimeException("차감할 금액은 0보다 커야 합니다.");
        }

        if (this.amount < amount) {
            throw new RuntimeException("잔액 부족: 현재 잔액 = " + this.amount);
        }

        this.amount -= amount;
    }

    public void increase(Long amount){
        if (amount == null || amount <= 0) {
            throw new RuntimeException("증액할 금액은 0보다 커야 합니다.");
        }

        this.amount += amount;
    }

}
