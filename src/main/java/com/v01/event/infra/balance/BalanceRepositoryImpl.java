package com.v01.event.infra.balance;

import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.balance.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceLocalDatabase balanceLocalDatabase;

    @Override
    public Balance updateBalance(Balance balance) {
        return balanceLocalDatabase.updateBalance(balance);
    }

    @Override
    public Balance findByUserId(Long userId) {
        return balanceLocalDatabase.findByUserId(userId);
    }
}
