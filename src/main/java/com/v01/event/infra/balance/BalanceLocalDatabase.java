package com.v01.event.infra.balance;

import com.v01.event.domain.balance.Balance;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class BalanceLocalDatabase {

    private final Map<Long, Balance> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Balance findByUserId(Long userId) {
        for (Balance balance : localDb.values()) {
            if (balance.getUserId().equals(userId)) {
                return balance;
            }
        }
        return null;
    }

    public Balance updateBalance(Balance balance) {
        localDb.put(balance.getBalanceId(), balance);
        return localDb.get(balance.getBalanceId());
    }

    public void save(Balance balance) {
        Long id = idGenerator.incrementAndGet();
        balance.setBalanceId(id);
        localDb.put(id,balance);
    }



}
