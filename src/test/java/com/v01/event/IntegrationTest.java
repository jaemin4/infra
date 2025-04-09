//package com.v01.event;
//
//import com.v01.event.domain.balance.BalanceService;
//import com.v01.event.domain.payment.PaymentHistoryService;
//import com.v01.event.domain.product.ProductService;
//import com.v01.event.interfaces.dto.res.ResCompletePaymentDto;
//import com.v01.event.interfaces.front.PaymentFrontService;
//import com.v01.event.interfaces.param.PaymentParam;
//import org.junit.jupiter.api.DisplayName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class IntegrationTest {
//
//    @Autowired
//    PaymentFrontService paymentFrontService;
//
//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    BalanceService balanceService;
//
//    @Autowired
//    PaymentHistoryService paymentHistoryService;
//
//    @DisplayName("결제 테스트")
//    void paymentCompleteTest(){
//        PaymentParam param = new PaymentParam(
//                1L,
//                1L,
//                1L
//        );
//        ResCompletePaymentDto DTO = paymentFrontService.completePayment(param);
//
//        DTO.getProductName()
//
//    }
//
//
//
//
//}
