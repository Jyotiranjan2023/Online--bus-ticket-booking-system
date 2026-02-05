package com.busbooking.service.impl;

import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.busbooking.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    @Override
    public String createOrder(Double amount) {

        try {
            RazorpayClient client =
                    new RazorpayClient(razorpayKeyId, razorpaySecret);

            JSONObject options = new JSONObject();
            options.put("amount", amount.intValue() * 100); // paise
            options.put("currency", "INR");
            options.put("receipt", "rcpt_" + System.currentTimeMillis());

            Order order = client.orders.create(options);
            return order.get("id");

        } catch (Exception e) {
            throw new RuntimeException("Unable to create Razorpay order", e);
        }
    }
    @Override
    public boolean verifyPayment(String orderId, String paymentId, String signature) {

        System.out.println("===== VERIFY PAYMENT =====");
        System.out.println("orderId     = " + orderId);
        System.out.println("paymentId   = " + paymentId);
        System.out.println("signature   = " + signature);
        System.out.println("secret null? " + (razorpaySecret == null));

        try {
            String payload = orderId + "|" + paymentId;

            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(
                    razorpaySecret.getBytes(StandardCharsets.UTF_8),
                    "HmacSHA256"
            );

            mac.init(key);
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            String generatedSignature = bytesToHex(hash);

            System.out.println("generatedSignature = " + generatedSignature);

            return generatedSignature.equals(signature);

        } catch (Exception e) {
            e.printStackTrace();   // 🔴 DO NOT REMOVE
            return false;
        }
    }


    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
