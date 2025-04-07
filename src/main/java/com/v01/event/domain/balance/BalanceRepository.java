package com.v01.event.domain.balance;

public interface BalanceRepository {

    void updateOrSaveBalance(final Balance balance);

    Balance findByUserId(final Long userId);

}
