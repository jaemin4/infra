package com.v01.event.domain.balance;

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

    public Balance useableBalnce(final ReqUseBalanceDto DTO) {

        Balance balance = balanceRepository.findByUserId(DTO.getUserId());
        if(balance == null) {
            throw new RuntimeException(DTO.getUserId().toString() + " : 해당 유저가 존재하지 않습니다");
        }

        if(balance.getAmount() < DTO.getAmount()) {
            throw new RuntimeException("잔액이 부족합니다.");
        }

        balance.setAmount(balance.getAmount() - DTO.getAmount());
        return balanceRepository.updateBalance(balance);
    }

    public void recordBalanceHistory(final ReqRecordBalanceHistoryDto DTO) {
        balanceHistoryRepository.save(new BalanceHistory(
                DTO.getUserId(),
                DTO.getAmount()
        ));

    }


}
