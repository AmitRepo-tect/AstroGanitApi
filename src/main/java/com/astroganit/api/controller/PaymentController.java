package com.astroganit.api.controller;

import org.springframework.web.bind.annotation.*;

import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.model.CreateOrderRequest;
import com.astroganit.api.model.VerifyPaymentRequest;
import com.astroganit.api.model.VerifyPaymentResponse;
import com.astroganit.api.serviceImpl.PaymentService;
import com.astroganit.api.serviceImpl.SubscriptionService;
import com.razorpay.RazorpayException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private SubscriptionService subscriptionService;
	@Value("${razorpay.key_secret}")
	private String razorpayKeySecret;
	@Value("${razorpay.key_id}")
	private String razorpayKeyId;

	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws RazorpayException {
		String order = paymentService.createOrder(createOrderRequest);
		return ResponseEntity.ok(order);
	}

	@PostMapping("/update-status")
	public ResponseEntity<?> updateStatus(@RequestBody Map<String, String> payload) {
		paymentService.updatePaymentStatus(payload.get("orderId"), payload.get("paymentId"), payload.get("status"));
		return ResponseEntity.ok("Payment status updated");
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verifyPayment(@RequestBody VerifyPaymentRequest request) {
		String razorpayOrderId = request.getRazorpayOrderId();
		String razorpayPaymentId = request.getRazorpayPaymentId();
		String razorpaySignature = request.getRazorpaySignature();
		int userId = Integer.parseInt(request.getUserId());
		int planId = Integer.parseInt(request.getPlanId());
		System.out.println(
				razorpayOrderId + "--" + razorpayPaymentId + "--" + razorpaySignature + "--" + userId + "--" + planId);
		int durationDays = request.getDurationDays();

		try {
			// 1️⃣ Verify signature
			String payload = razorpayOrderId + "|" + razorpayPaymentId;
			String generatedSignature = hmacSha256(payload, razorpayKeySecret);

			/*
			 * if (!generatedSignature.equals(razorpaySignature)) { return
			 * ResponseEntity.badRequest().body("Invalid payment signature"); }
			 */

			// 2️⃣ Activate subscription
			VerifyPaymentResponse response= subscriptionService.activateSubscription(userId, planId, durationDays,
					razorpayPaymentId);

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Payment verification failed");
		}
	}

	// Utility: generate HMAC SHA256
	/*
	 * private String hmacSha256(String data, String key) throws Exception { Mac mac
	 * = Mac.getInstance("HmacSHA256"); SecretKeySpec secretKey = new
	 * SecretKeySpec(key.getBytes(), "HmacSHA256"); mac.init(secretKey); byte[] hash
	 * = mac.doFinal(data.getBytes()); return new
	 * String(Base64.getEncoder().encode(hash)); }
	 */
	public String hmacSha256(String data, String key) throws Exception {
	    Mac mac = Mac.getInstance("HmacSHA256");
	    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
	    mac.init(secretKey);
	    byte[] hash = mac.doFinal(data.getBytes());

	    // Convert bytes to lowercase hex
	    StringBuilder hexString = new StringBuilder();
	    for (byte b : hash) {
	        String hex = Integer.toHexString(0xff & b);
	        if (hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	/*
	 * @PostMapping("/verify") public ResponseEntity<?>
	 * verifyAndUpdatePayment(@RequestBody Map<String, String> request) { boolean
	 * isValid = paymentService.verifySignature( request.get("razorpay_order_id"),
	 * request.get("razorpay_payment_id"), request.get("razorpay_signature") );
	 * 
	 * if (!isValid) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature"); }
	 * 
	 * // If valid, update DB paymentService.updatePaymentStatus(
	 * request.get("razorpay_order_id"), request.get("razorpay_payment_id"),
	 * "captured" );
	 * 
	 * return ResponseEntity.ok("Payment verified and updated successfully"); }
	 */

}
