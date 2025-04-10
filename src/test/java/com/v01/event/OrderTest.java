package com.v01.event;

import com.v01.event.application.BalanceFrontService;
import com.v01.event.application.OrderFrontService;
import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.order.Order;
import com.v01.event.domain.order.OrderService;
import com.v01.event.domain.product.Product;
import com.v01.event.domain.product.ProductService;
import com.v01.event.infra.balance.BalanceHistoryLocalDatabase;
import com.v01.event.infra.balance.BalanceLocalDatabase;
import com.v01.event.infra.order.OrderLocalDatabase;
import com.v01.event.infra.product.ProductLocalDatabase;
import com.v01.event.interfaces.model.dto.res.ResCompleteOrderDto;
import com.v01.event.interfaces.model.param.OrderItemParam;
import com.v01.event.interfaces.model.param.OrderParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderTest {

    @Autowired
    ProductLocalDatabase productLocalDatabase;

    @Autowired
    private BalanceLocalDatabase balanceLocalDatabase;

    @Autowired
    private OrderFrontService orderFrontService;

    @Autowired
    private OrderService orderService;

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
    @DisplayName("주문 생성 통합 테스트(중복 검증 → 재고 차감 → 금액 계산 → 주문 저장)")
    @Test
    void completeOrderIntegrationTest() {
        // given
        Long userId = 1L;
        List<OrderItemParam> items = List.of(
                new OrderItemParam(1L, 1L), // 상품 1개
                new OrderItemParam(2L, 2L)  // 상품 2개
        );
        OrderParam param = new OrderParam(userId, items);

        // when
        ResCompleteOrderDto result = orderFrontService.completeOrder(param);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("CREATED", result.getStatus());
        Assertions.assertTrue(result.getTotalAmount() > 0);

        // todo 주문이 실제로 저장됐는지 검증
        Order savedOrder = orderService.getOrderById(result.getOrderId());
        Assertions.assertEquals(result.getOrderId(), savedOrder.getOrderId());
        Assertions.assertEquals(result.getTotalAmount(), savedOrder.getTotalAmount());
        Assertions.assertEquals(2, savedOrder.getItems().size());

        // todo 재고가 차감됐는지 검증
        for (OrderItemParam item : items) {
            Long productId = item.getProductId();
            Long orderedQty = item.getQuantity();
            Product product = productLocalDatabase.findByProductId(productId).orElseThrow();
            Assertions.assertTrue(product.getProductQuantity() < 100L); // 초기 수량보다 적어짐
        }
    }



}
