package com.v01.event.domain.balance;

import java.util.Optional;

public interface BalanceRepository {

    Balance updateBalance(final Balance balance);

    Optional<Balance> findByUserId(final Long userId);

}
