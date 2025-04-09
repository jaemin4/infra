package com.v01.event.infra.payment;

import com.v01.event.domain.payment.PaymentHistory;
import com.v01.event.domain.payment.PaymentHistoryRepository;
import com.v01.event.support.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentRepositoryImpl implements PaymentHistoryRepository {

    private final PaymentHistoryLocalDatabase paymentLocalRepository;

    @Override
    public void save(PaymentHistory paymentHistory) {
        paymentLocalRepository.save(paymentHistory);

        log.info("Saved Payment {}", Utils.toJson(paymentHistory));
    }
}
