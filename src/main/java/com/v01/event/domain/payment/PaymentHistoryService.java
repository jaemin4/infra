package com.v01.event.domain.payment;

import com.v01.event.interfaces.model.dto.req.ReqPaymentHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    public void recordPaymentHistory(ReqPaymentHistoryDto DTO) {
        paymentHistoryRepository.save(new PaymentHistory(
                DTO.getUserId(),
                DTO.getAmount(),
                DTO.getOrderId(),
                DTO.getTransactionId(),
                DTO.getStatus()
        ));
    }


}
