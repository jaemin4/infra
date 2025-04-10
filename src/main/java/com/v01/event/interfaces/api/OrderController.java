package com.v01.event.interfaces.api;

import com.v01.event.application.OrderFrontService;
import com.v01.event.domain.order.OrderService;
import com.v01.event.interfaces.model.dto.res.ResCompleteOrderDto;
import com.v01.event.interfaces.model.param.OrderParam;
import com.v01.event.interfaces.model.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderFrontService orderFrontService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResCompleteOrderDto>> createOrder(@RequestBody OrderParam param) {
        ResCompleteOrderDto resDto = orderFrontService.completeOrder(param);

        ApiResponse<ResCompleteOrderDto> response = new ApiResponse<>("결제 완료", resDto);
        return ResponseEntity.ok(response);
    }


}
