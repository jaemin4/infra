package com.v01.event.interfaces.front;

import com.v01.event.domain.balance.BalanceService;
import com.v01.event.domain.payment.PaymentHistoryService;
import com.v01.event.domain.product.Product;
import com.v01.event.domain.product.ProductService;
import com.v01.event.interfaces.dto.res.ResCompletePaymentDto;
import com.v01.event.interfaces.dto.req.ReqDecreaseStockDto;
import com.v01.event.interfaces.dto.req.ReqPaymentHistoryDto;
import com.v01.event.interfaces.dto.req.ReqRecordBalanceHistoryDto;
import com.v01.event.interfaces.dto.req.ReqUseBalanceDto;
import com.v01.event.interfaces.param.PaymentParam;
import com.v01.event.support.enums.BalanceReason;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentFrontService {

    private final BalanceService balanceService;
    private final PaymentHistoryService paymentHistoryService;
    private final ProductService productService;

    public ResCompletePaymentDto completePayment(PaymentParam param) {
        final String balanceReason = BalanceReason.PURCHASE.name();

        // TODO 재고 감소 + 재고 유효성
        Product product = productService.decreaseAbleStock(new ReqDecreaseStockDto(
                param.getProductId(),param.getQuantity()
        ));

        log.info("재고 유효성 통과");

        // TODO 잔액 사용 + 유저 유효성
        balanceService.useableBalnce(new ReqUseBalanceDto(
                param.getUserId(),product.getProductPrice())
        );

        log.info("잔액 유효성 통과");

        // TODO 잔액 변경내역 저장
        balanceService.recordBalanceHistory(new ReqRecordBalanceHistoryDto(
                param.getUserId(),product.getProductPrice())
        );

        log.info("잔역 변경내역 저장 통과");

        // TODO 결제내역 저장
        paymentHistoryService.recordPaymentHistory(new ReqPaymentHistoryDto(
                param.getUserId(),product.getProductPrice(),balanceReason)
        );

        log.info("결제내역 저장 통과");


        return new ResCompletePaymentDto(
                product.getProductName(),
                product.getProductPrice()
        );
    }


}
