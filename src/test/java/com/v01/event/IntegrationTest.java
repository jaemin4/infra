package com.v01.event;

import com.v01.event.application.BalanceFrontService;
import com.v01.event.application.PaymentFrontService;
import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.product.Product;
import com.v01.event.infra.balance.BalanceHistoryLocalDatabase;
import com.v01.event.infra.balance.BalanceLocalDatabase;
import com.v01.event.infra.payment.PaymentHistoryLocalDatabase;
import com.v01.event.infra.product.ProductLocalDatabase;
import com.v01.event.interfaces.model.param.CompleteChargeParam;
import com.v01.event.interfaces.model.param.PaymentParam;
import com.v01.event.interfaces.model.param.ReqValidateNoDuplicateItemsDto;
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

    @Autowired
    private BalanceFrontService balanceFrontService;

    /*
      todo
        product(ProductId):1L,2L / Balance(BalanceId,UserId):1L,2L
        각 케이스마다 해당 ID값 Default 저장
    */
    @BeforeEach
    public void beforeEach() {
        Product defaultProduct = new Product("컴퓨터", 1_000_000L, 10L);
        Product defaultProduct2 = new Product( "마우스", 10000L, 10L);
        productLocalDatabase.save(defaultProduct);
        productLocalDatabase.save(defaultProduct2);

        Balance defaultUser = new Balance(1L,100_000_000L);
        Balance defaultUser2 = new Balance(2L,0L);
        balanceLocalDatabase.save(defaultUser);
        balanceLocalDatabase.save(defaultUser2);
    }

    @DisplayName("결제 테스트(잔액 차감, 재고 감소, 결제 및 잔액 이력 저장을 검증한다)")
    @Test
    void paymentCompleteTest() {
        Long userId = 1L;

        List<ReqValidateNoDuplicateItemsDto> products = List.of(
                new ReqValidateNoDuplicateItemsDto(1L, 1L), // 컴퓨터 1개
                new ReqValidateNoDuplicateItemsDto(2L, 2L)  // 마우스 2개
        );

        paymentFrontService.completePayment(new PaymentParam(userId, products));

        // 총 결제 금액 계산
        long totalAmount = products.stream()
                .mapToLong(p -> productLocalDatabase.findByProductId(p.getProductId())
                        .map(product -> product.getProductPrice() * p.getQuantity())
                        .orElseThrow())
                .sum();

        // todo 사용자 잔액 검증
        Balance balance = balanceLocalDatabase.findByUserId(userId).orElseThrow();
        assertEquals(100_000_000L - totalAmount, balance.getAmount());

        // todo 재고 감소 검증
        assertEquals(9L, productLocalDatabase.findByProductId(1L).orElseThrow().getProductQuantity()); // 10 - 1
        assertEquals(8L, productLocalDatabase.findByProductId(2L).orElseThrow().getProductQuantity()); // 10 - 2

        // todo 결제 내역 검증
        var payment = paymentHistoryLocalDatabase.findAll().getFirst();
        assertEquals(userId, payment.getUserId());
        assertEquals(-totalAmount, payment.getAmount());

        // todo 잔액 사용 이력 검증
        var balanceHistory = balanceHistoryLocalDatabase.findAll().getFirst();
        assertEquals(userId, balanceHistory.getUserId());
        assertEquals(-totalAmount, balanceHistory.getAmount());
    }

    @DisplayName("잔액충전 테스트(잔액 충전, 충전 이력 저장)")
    @Test
    void chargeBalanceAndRecordHistory() {
        Long userId = 2L;
        Long amount = 5000L;

        balanceFrontService.completeCharge(new CompleteChargeParam(userId, amount));

        Balance balance = balanceLocalDatabase.findByUserId(userId).orElseThrow();
        assertEquals(amount, balance.getAmount());

        var historyList = balanceHistoryLocalDatabase.findAll();
        assertEquals(1, historyList.size());

        var history = historyList.getFirst();
        assertEquals(userId, history.getUserId());
        assertEquals(amount, history.getAmount());
    }




}
