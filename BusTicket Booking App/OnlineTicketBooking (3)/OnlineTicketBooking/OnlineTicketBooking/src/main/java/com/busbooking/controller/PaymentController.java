package com.busbooking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busbooking.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // 1️⃣ Create Razorpay order
    @PostMapping("/create-order")
    public Map<String, String> createOrder(@RequestParam Double amount) {

        String orderId = paymentService.createOrder(amount);

        return Map.of(
            "orderId", orderId
        );
    }

    @PostMapping("/verify")
    public Map<String, String> verifyPayment(@RequestBody Map<String, String> data) {

        boolean success = paymentService.verifyPayment(
                data.get("razorpay_order_id"),
                data.get("razorpay_payment_id"),
                data.get("razorpay_signature")
        );

        return Map.of(
                "status", success ? "PAYMENT_SUCCESS" : "PAYMENT_FAILED"
        );
    }

}
