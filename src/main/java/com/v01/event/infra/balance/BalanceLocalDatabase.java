package com.v01.event.infra.balance;

import com.v01.event.domain.balance.Balance;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class BalanceLocalDatabase {

    private final Map<Long, Balance> localDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @PostConstruct
    public void initDefaultUser() {
        // 기본 유저 생성: userId=1, amount=100000
        Balance defaultUser = new Balance();
        defaultUser.setUserId(1L);
        defaultUser.setAmount(100_000L);

        long id = idGenerator.incrementAndGet();
        defaultUser.setBalanceId(id);
        localDb.put(id, defaultUser);

        log.info("✅ 기본 유저 생성 완료: {}", defaultUser);
    }


    public Balance findByUserId(Long userId) {
        for (Balance balance : localDb.values()) {
            if (balance.getUserId().equals(userId)) {
                return balance;
            }
        }
        return null;
    }

    public void updateOrSaveBalance(Balance balance) {
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
