package com.astroganit.api.serviceImpl;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.astroganit.api.entities.Payment;
import com.astroganit.api.model.CreateOrderRequest;
import com.astroganit.api.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Value("${razorpay.key_id}")
    private String razorpayKey;

    @Value("${razorpay.key_secret}")
    private String razorpaySecret;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public String createOrder(CreateOrderRequest createOrderRequest) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", createOrderRequest.getAmount() * 100); // in paise
        orderRequest.put("currency", createOrderRequest.getCurrency());
        orderRequest.put("payment_capture", 1);
        Order order = client.orders.create(orderRequest);
        Payment payment = new Payment();
        payment.setUserId(createOrderRequest.getUserId());
        payment.setAmount(createOrderRequest.getAmount());
        payment.setCurrency(createOrderRequest.getCurrency());
        payment.setPaymentFor(createOrderRequest.getPaymentFor());
        payment.setReferenceId(createOrderRequest.getReferenceId());
        payment.setOrderId(order.get("id"));
        payment.setStatus(order.get("status"));
        payment.setPaymentMethod("payment_method");
      
        paymentRepository.save(payment);

        return order.toString();
    }

    public void updatePaymentStatus(String orderId, String paymentId, String status) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setOrderId(paymentId);
        payment.setStatus(status);
        paymentRepository.save(payment);
    }
}
