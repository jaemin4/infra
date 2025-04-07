package com.v01.event.domain.balance;

import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    public void decreaseBalance(Long userId, int amount) {
        System.out.println("[Balance] 잔액 차감: userId=" + userId + ", amount=" + amount);
    }
}
