package com.v01.event;

import com.v01.event.application.PaymentFrontService;
import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.product.Product;
import com.v01.event.infra.balance.BalanceHistoryLocalDatabase;
import com.v01.event.infra.balance.BalanceLocalDatabase;
import com.v01.event.infra.payment.PaymentHistoryLocalDatabase;
import com.v01.event.infra.product.ProductLocalDatabase;
import com.v01.event.interfaces.model.param.PaymentParam;
import com.v01.event.interfaces.model.param.ReqPayProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    PaymentFrontService paymentFrontService;

    @Autowired
    PaymentHistoryLocalDatabase paymentHistoryLocalDatabase;

    @Autowired
    BalanceLocalDatabase balanceLocalDatabase;

    @Autowired
    ProductLocalDatabase productLocalDatabase;

    @Autowired
    private BalanceHistoryLocalDatabase balanceHistoryLocalDatabase;

    @BeforeEach
    public void beforeEach() {
        Product defaultProduct = new Product(1L, "컴퓨터", 1_000_000L, 10L);
        Product defaultProduct2 = new Product(2L, "마우스", 10000L, 10L);
        productLocalDatabase.save(defaultProduct);
        productLocalDatabase.save(defaultProduct2);

        Balance defaultUser = new Balance(1L,100_000_000L);
        balanceLocalDatabase.save(defaultUser);
    }

    @DisplayName("결제 테스트")
    @Test
    void paymentCompleteTest() {
        Long testUserId = 1L;

        Long testProductId = 1L;
        Long testQuantity = 1L;

        Long testProductId2 = 2L;
        Long testQuantity2 = 2L;

        List<ReqPayProduct> listProduct = List.of(
                new ReqPayProduct(testProductId, testQuantity),
                new ReqPayProduct(testProductId2, testQuantity2)
        );

        paymentFrontService.completePayment(new PaymentParam(testUserId, listProduct));

        //총 결제 금액 계산
        long expectedTotalAmount = listProduct.stream()
                .mapToLong(p -> {
                    Product product = productLocalDatabase.findByProductId(p.getProductId()).orElseThrow();
                    return product.getProductPrice() * p.getQuantity();
                })
                .sum();

        // todo 사용자 잔액 검증
        Balance balance = balanceLocalDatabase.findByUserId(testUserId);
        assertEquals(100_000_000L - expectedTotalAmount, balance.getAmount());

        // todo 재고 감소 검증
        Product product1 = productLocalDatabase.findByProductId(testProductId).orElseThrow();
        Product product2 = productLocalDatabase.findByProductId(testProductId2).orElseThrow();

        assertEquals(10L - testQuantity, product1.getProductQuantity());
        assertEquals(10L - testQuantity2, product2.getProductQuantity());

        // todo 결제 내역 저장 검증
        assertEquals(1, paymentHistoryLocalDatabase.findAll().size());
        var savedPayment = paymentHistoryLocalDatabase.findAll().getFirst();
        assertEquals(testUserId, savedPayment.getUserId());
        assertEquals(expectedTotalAmount * (-1), savedPayment.getAmount());

        // todo 잔액 사용 내역 저장 검증
        assertEquals(1, balanceHistoryLocalDatabase.findAll().size());
        var balanceHistory = balanceHistoryLocalDatabase.findAll().getFirst();
        assertEquals(testUserId, balanceHistory.getUserId());
        assertEquals(expectedTotalAmount * (-1), balanceHistory.getAmount());
    }
}
