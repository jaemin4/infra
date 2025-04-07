package com.v01.event.interfaces.api;

import com.v01.event.domain.payment.PaymentService;
import com.v01.event.interfaces.param.PaymentParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> completePayment(@RequestBody PaymentParam param) {
        paymentService.completePayment(param);
        return ResponseEntity.ok("결제 완료");
    }
}
