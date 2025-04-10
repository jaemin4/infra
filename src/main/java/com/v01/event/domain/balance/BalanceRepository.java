package com.v01.event.domain.balance;

public interface BalanceRepository {

    Balance updateBalance(final Balance balance);

    Balance findByUserId(final Long userId);

}
