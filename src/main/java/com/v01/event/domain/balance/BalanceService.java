package com.v01.event.domain.balance;

import com.v01.event.interfaces.model.dto.req.ReqChargeBalanceDto;
import com.v01.event.interfaces.model.dto.req.ReqRecordBalanceHistoryDto;
import com.v01.event.interfaces.model.dto.req.ReqUseBalanceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceHistoryRepository balanceHistoryRepository;

    public void recordBalanceHistory(ReqRecordBalanceHistoryDto DTO) {
        balanceHistoryRepository.save(new BalanceHistory(
                DTO.getUserId(),DTO.getAmount()
        ));

    }

    public Balance chargeableBalance(ReqChargeBalanceDto DTO) {
        Long userId = DTO.getUserId();
        Long amount = DTO.getAmount();

        Balance balance = balanceRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException(userId.toString() + " : 해당 유저가 존재하지 않습니다")
        );

        balance.setAmount(balance.getAmount() + amount);
        return balanceRepository.updateBalance(balance);
    }

    public Balance usableBalance(ReqUseBalanceDto DTO) {
        Long userId = DTO.getUserId();
        Long amount = DTO.getAmount();

        Balance balance = balanceRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException(userId.toString() + " : 해당 유저가 존재하지 않습니다")
        );

        if(balance.getAmount() < amount) {
            throw new RuntimeException("잔액이 부족합니다.");
        }

        balance.setAmount(balance.getAmount() - amount);
        return balanceRepository.updateBalance(balance);
    }

}
