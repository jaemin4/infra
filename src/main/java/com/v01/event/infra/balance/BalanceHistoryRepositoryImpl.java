package com.v01.event.infra.balance;

import com.v01.event.domain.balance.BalanceHistory;
import com.v01.event.domain.balance.BalanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BalanceHistoryRepositoryImpl implements BalanceHistoryRepository {

    private final BalanceHistoryLocalDatabase balanceHistoryLocalDatabase;

    @Override
    public void save(BalanceHistory balanceHistory) {
        balanceHistoryLocalDatabase.save(balanceHistory);
    }
}
