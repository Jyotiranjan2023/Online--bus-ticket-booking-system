package com.busbooking.service;

public interface PaymentService {

    String createOrder(Double amount);

    boolean verifyPayment(
        String orderId,
        String paymentId,
        String signature
    );
}

