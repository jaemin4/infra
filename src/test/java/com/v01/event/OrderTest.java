package com.v01.event;

import com.v01.event.application.BalanceFrontService;
import com.v01.event.application.OrderFrontService;
import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.order.Order;
import com.v01.event.domain.order.OrderService;
import com.v01.event.domain.product.Product;
import com.v01.event.infra.balance.BalanceHistoryLocalDatabase;
import com.v01.event.infra.balance.BalanceLocalDatabase;
import com.v01.event.infra.order.OrderLocalDatabase;
import com.v01.event.infra.product.ProductLocalDatabase;
import com.v01.event.interfaces.model.dto.res.ResCompleteOrderDto;
import com.v01.event.interfaces.model.param.OrderItemParam;
import com.v01.event.interfaces.model.param.OrderParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private BalanceFrontService balanceFrontService;

    @Autowired
    private OrderLocalDatabase orderLocalDatabase;

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
    @Test
    void testCreateOrder_shouldSaveOrderCorrectly() {
        // given
        Long userId = 1L;
        Long productId = 2L;
        Long productQuantity = 2L;

        OrderParam orderParam = new OrderParam(
                userId,
                List.of(new OrderItemParam(productId, productQuantity)) // 마우스 2개
        );

        // when
        ResCompleteOrderDto result = orderFrontService.completeOrder(orderParam);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getOrderId());
        Assertions.assertEquals("CREATED", result.getStatus());
        Assertions.assertEquals(20_000L, result.getTotalAmount()); // 10_000 * 2

        Order savedOrder = orderService.getOrderById(result.getOrderId());

        Assertions.assertEquals(1, savedOrder.getItems().size());
        Assertions.assertEquals(productId, savedOrder.getItems().getFirst().getProductId());
        Assertions.assertEquals(2L, savedOrder.getItems().getFirst().getQuantity());
    }


}
