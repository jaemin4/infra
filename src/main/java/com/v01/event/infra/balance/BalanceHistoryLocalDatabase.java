package com.v01.event.infra.balance;

import com.v01.event.domain.balance.BalanceHistory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class BalanceHistoryLocalDatabase {

    private Map<Long, BalanceHistory> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public void save(final BalanceHistory balanceHistory) {
        Long id = idGenerator.incrementAndGet();
        localDb.put(id,balanceHistory);
    }

}
