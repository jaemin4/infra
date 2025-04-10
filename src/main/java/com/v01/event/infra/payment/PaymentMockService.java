package com.v01.event.infra.payment;

import com.v01.event.interfaces.model.param.PaymentMockParam;
import com.v01.event.interfaces.model.rest.MockPaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMockService {
    private final RestTemplate restTemplate;

    private static final String MOCK_API_URL = "https://67ed717f4387d9117bbda6b1.mockapi.io/api/pay/test";

    public MockPaymentResponse callAndValidateMockApi(PaymentMockParam request) {
        try {
            log.info("[MOCK 결제사] POST 호출: {}", MOCK_API_URL);
            log.info("요청 바디: orderId={}, userId={}, amount={}",
                    request.getOrderId(), request.getUserId(), request.getAmount());

            // 실제 호출은 하지만 응답은 무시
            restTemplate.postForEntity(
                    MOCK_API_URL,
                    request,
                    String.class
            );

            // 고정 응답 생성
            MockPaymentResponse fixedResponse = new MockPaymentResponse();
            fixedResponse.setStatus("200");
            fixedResponse.setTransactionId("fixed-transaction-id");
            fixedResponse.setMessage("결제 성공 (Mock 고정 응답)");

            log.info("고정 결제 성공 응답 반환");
            return fixedResponse;

        } catch (Exception e) {
            log.error("Mock 결제 API 호출 중 예외 발생", e);
            throw new RuntimeException("Mock 결제 API 호출 실패", e);
        }
    }
}
