package com.v01.event.interfaces.front;

import com.v01.event.domain.balance.BalanceService;
import com.v01.event.domain.payment.PaymentHistoryService;
import com.v01.event.interfaces.dto.req.ReqPaymentHistoryDto;
import com.v01.event.interfaces.dto.req.ReqRecordBalanceHistoryDto;
import com.v01.event.interfaces.dto.req.ReqUseBalanceDto;
import com.v01.event.interfaces.param.PaymentParam;
import com.v01.event.support.enums.BalanceReason;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFrontService {

    private final BalanceService balanceService;
    private final PaymentHistoryService paymentHistoryService;

    public void completePayment(final PaymentParam param) {
        final String balanceReason = BalanceReason.PURCHASE.name();

        // TODO 재고 감소




        // TODO 잔액 사용
        balanceService.useBalance(new ReqUseBalanceDto(
                param.getUserId(),param.getAmount())
        );

        // TODO 잔액 변경내역 저장
        balanceService.recordBalanceHistory(new ReqRecordBalanceHistoryDto(
                param.getUserId(),param.getAmount())
        );

        // TODO 갤제내역 저장
        paymentHistoryService.recordPaymentHistory(new ReqPaymentHistoryDto(
                param.getUserId(),param.getAmount(),balanceReason)
        );
    }


}
