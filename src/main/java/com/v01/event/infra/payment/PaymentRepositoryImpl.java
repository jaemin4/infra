package com.v01.event.infra.payment;

import com.v01.event.domain.payment.Payment;
import com.v01.event.domain.payment.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Override
    public void save(Payment payment) {
        System.out.println("결제 : " + payment);
    }
}
