package com.v01.event.infra.balance;

import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.balance.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceLocalDatabase balanceLocalDatabase;

    @Override
    public void updateOrSaveBalance(final Balance balance) {
        balanceLocalDatabase.updateOrSaveBalance(balance);
    }
}
