package com.v01.event;

import com.v01.event.application.PaymentFrontService;
import com.v01.event.domain.balance.BalanceHistoryRepository;
import com.v01.event.domain.balance.BalanceRepository;
import com.v01.event.domain.payment.PaymentHistoryRepository;
import com.v01.event.domain.product.ProductRepository;
import com.v01.event.interfaces.model.param.PaymentParam;
import com.v01.event.interfaces.model.param.ReqPayProduct;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    PaymentFrontService paymentFrontService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    BalanceHistoryRepository BalanceHistoryRepository;

    @DisplayName("결제 테스트")
    void paymentCompleteTest(){



        List<ReqPayProduct> listProduct = List.of(
                new ReqPayProduct(1L,1L),
                new ReqPayProduct(2L,2L)
        );

        PaymentParam param = new PaymentParam(1L,listProduct);
        paymentFrontService.completePayment(param);


    }




}
