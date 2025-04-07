package com.v01.event.domain.balance;

import com.v01.event.interfaces.dto.req.ReqRecordBalanceHistoryDto;
import com.v01.event.interfaces.dto.req.ReqUseBalanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceHistoryRepository balanceHistoryRepository;

    public void useBalance(final ReqUseBalanceDto DTO) {
        Balance balance = balanceRepository.findByUserId(DTO.getUserId());
        balance.decrease(DTO.getAmount());

        balanceRepository.updateOrSaveBalance(balance);
    }

    public void recordBalanceHistory(final ReqRecordBalanceHistoryDto DTO) {
        balanceHistoryRepository.save(new BalanceHistory(
                DTO.getUserId(),
                DTO.getAmount()
        ));

    }


}
