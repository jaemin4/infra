package com.v01.event.domain.payment;


import com.v01.event.interfaces.param.PaymentParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ApplicationEventPublisher eventPublisher;
    private final PaymentRepository paymentRepository;

    public void completePayment(PaymentParam param) {
        Payment payment = new Payment(param.getUserId(), param.getOrderId(), param.getAmount());
        paymentRepository.save(payment);

        eventPublisher.publishEvent(new PaymentCompletedEvent(
                param.getUserId(),
                param.getOrderId(),
                param.getAmount()
        ));

    }

}
