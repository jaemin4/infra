package com.v01.event.application;

import com.v01.event.domain.order.Order;
import com.v01.event.domain.order.OrderService;
import com.v01.event.domain.product.Product;
import com.v01.event.domain.product.ProductService;
import com.v01.event.interfaces.model.dto.req.ReqCalculateTotalAmountDto;
import com.v01.event.interfaces.model.dto.req.ReqDecreaseStockDto;
import com.v01.event.interfaces.model.dto.req.ReqSaveOrderDto;
import com.v01.event.interfaces.model.dto.res.ResCompleteOrderDto;
import com.v01.event.interfaces.model.param.OrderItemParam;
import com.v01.event.interfaces.model.param.OrderParam;
import com.v01.event.interfaces.model.param.ReqValidateNoDuplicateItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFrontService {

    private final OrderService orderService;
    private final ProductService productService;

    public ResCompleteOrderDto completeOrder(OrderParam orderParam) {
        // todo Param
        Long paramUserId = orderParam.getUserId();
        List<OrderItemParam> paramItems = orderParam.getItems();

        // todo 프론트측 중복 상품 파람 검증
        List<ReqValidateNoDuplicateItemsDto> reqDuItems = paramItems.stream()
                .map(item -> new ReqValidateNoDuplicateItemsDto(item.getProductId(), item.getQuantity()))
                .toList();

        productService.validateNoDuplicateProducts(reqDuItems);

        // todo 재고 차감
        for (OrderItemParam item : orderParam.getItems()) {
            productService.decreaseAbleStock(
                    new ReqDecreaseStockDto(
                            item.getProductId(),
                            item.getQuantity()
                    )
            );
        }
        // todo 총액 계산
        List<ReqCalculateTotalAmountDto> amountItems = paramItems.stream()
                .map(item -> new ReqCalculateTotalAmountDto(item.getProductId(), item.getQuantity()))
                .toList();
        long totalAmount = productService.calculateTotalAmount(amountItems);

        // todo 주문 저장(대기상태 Created)
        Order order = orderService.createOrder(new ReqSaveOrderDto(
                paramUserId, paramItems, totalAmount
        ));

        return new ResCompleteOrderDto(
                order.getOrderId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems()
        );

    }
}
