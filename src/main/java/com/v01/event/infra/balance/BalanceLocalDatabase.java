package com.v01.event.infra.balance;

import com.v01.event.domain.balance.Balance;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class BalanceLocalDatabase {

    private final Map<Long, Balance> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public void updateOrSaveBalance(final Balance balance) {
        Long userId = balance.getUserId();

        // TODO 기존 Balance 찾아서 있으면 update, 없으면 새로 저장
        Optional<Long> existingId = localDb.entrySet().stream()
                .filter(e -> e.getValue().getUserId().equals(userId))
                .map(Map.Entry::getKey)
                .findFirst();

        if (existingId.isPresent()) {
            localDb.put(existingId.get(), balance);
            balance.setBalanceId(existingId.get());
        } else {
            long id = idGenerator.incrementAndGet();
            balance.setBalanceId(id);
            localDb.put(id, balance);
        }

    }


}
