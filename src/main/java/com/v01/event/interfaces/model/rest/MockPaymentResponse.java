package com.v01.event.interfaces.model.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MockPaymentResponse {
    private String status;
    private String transactionId;
    private String message;
    private String id;
}
