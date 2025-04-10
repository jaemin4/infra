package com.v01.event.infra.balance;

import com.v01.event.domain.balance.BalanceHistory;
import com.v01.event.domain.product.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class BalanceHistoryLocalDatabase {

    private Map<Long, BalanceHistory> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    public void save(BalanceHistory balanceHistory) {
        Long id = idGenerator.incrementAndGet();
        balanceHistory.setBalanceHistoryId(id);
        localDb.put(id,balanceHistory);
    }


}
