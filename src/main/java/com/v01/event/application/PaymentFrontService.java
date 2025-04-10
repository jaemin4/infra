package com.v01.event.application;

import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.balance.BalanceService;
import com.v01.event.domain.payment.PaymentHistoryService;
import com.v01.event.domain.product.Product;
import com.v01.event.domain.product.ProductService;
import com.v01.event.interfaces.model.dto.res.ResCompletePaymentDto;
import com.v01.event.interfaces.model.dto.req.ReqDecreaseStockDto;
import com.v01.event.interfaces.model.dto.req.ReqPaymentHistoryDto;
import com.v01.event.interfaces.model.dto.req.ReqRecordBalanceHistoryDto;
import com.v01.event.interfaces.model.dto.req.ReqUseBalanceDto;
import com.v01.event.interfaces.model.param.PaymentParam;
import com.v01.event.interfaces.model.param.ReqPayProduct;
import com.v01.event.support.enums.BalanceReason;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentFrontService {

    private final BalanceService balanceService;
    private final PaymentHistoryService paymentHistoryService;
    private final ProductService productService;


    public ResCompletePaymentDto completePayment(PaymentParam param) {
        final String balanceReason = BalanceReason.PURCHASE.name();
        List<Product> updatedProducts = new ArrayList<>();
        long totalAmount = 0L;

        // TODO  프론트측 중복 재고 검증
        productService.validateNoDuplicateProducts(param.getPayProductList());

        for (ReqPayProduct reqPayProduct : param.getPayProductList()) {
            Product product = productService.decreaseAbleStock(
                    new ReqDecreaseStockDto(
                            reqPayProduct.getProductId(),
                            reqPayProduct.getQuantity()
                    )
            );
            updatedProducts.add(product);
            totalAmount += product.getProductPrice() * reqPayProduct.getQuantity();
        }

        // TODO 잔액 사용 + 유저 유효성
        Balance balance = balanceService.usableBalance(new ReqUseBalanceDto(
                param.getUserId(),totalAmount)
        );

        // TODO 잔액 사용내역 저장
        balanceService.recordBalanceHistory(new ReqRecordBalanceHistoryDto(
                param.getUserId(),totalAmount * (-1))
        );

        // TODO 결제내역 저장
        paymentHistoryService.recordPaymentHistory(new ReqPaymentHistoryDto(
                param.getUserId(),totalAmount * (-1),balanceReason)
        );

        return new ResCompletePaymentDto(
                updatedProducts,
                balance.getAmount()
        );
    }


}
