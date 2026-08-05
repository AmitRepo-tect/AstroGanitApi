package com.astroganit.api.controller;

import org.springframework.web.bind.annotation.*;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.model.CreateOrderRequest;
import com.astroganit.api.model.RazorpayOrderDto;
import com.astroganit.api.model.RazorpayOrderResponse;
import com.astroganit.api.model.VerifyPaymentRequest;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.response.VerifyPaymentResponse;
import com.astroganit.api.serviceImpl.PaymentService;
import com.astroganit.api.util.ResultCode;
import com.astroganit.lib.panchang.util.AppResultConstant;
import com.astroganit.lib.panchang.util.Util;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Value("${razorpay.key_secret}")
	private String razorpayKeySecret;
	@Value("${razorpay.key_id}")
	private String razorpayKeyId;
	@Value("${razorpay.webhook.secret}")
	private String razorpayWebhookSecret;

	@PostMapping("/create-order")
	public ResponseEntity<ResponseNew<RazorpayOrderResponse>> createOrder(
			@RequestBody CreateOrderRequest createOrderRequest) {
		RazorpayOrderResponse order = paymentService.createOrderSafely(createOrderRequest);
		ResponseNew<RazorpayOrderResponse> response = new ResponseNew<>();
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.SUCCESS.getMessage());
		response.setErrorMessage("");
		response.setData(order);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/verify")
	public ResponseEntity<ResponseNew<VerifyPaymentResponse>> verifyPayment(@RequestBody VerifyPaymentRequest request) {

		// 1️⃣ Verify Razorpay Signature
		boolean isValid = Util.verifySignature(request.getRazorpayOrderId() + "|" + request.getRazorpayPaymentId(),
				request.getRazorpaySignature(), razorpayKeySecret);

		if (!isValid) {
			throw new AppException(ResultCode.INVALID_PAYMENT_SIGNATURE);
		}

		// 2️⃣ Finalize Payment
		VerifyPaymentResponse verifyPaymentResponse = paymentService.finalizePayment(request.getRazorpayOrderId(),
				request.getRazorpayPaymentId());

		// 3️⃣ Success Response
		ResponseNew<VerifyPaymentResponse> response = new ResponseNew<>();
		response.setMessage("Successfully");
		response.setErrorMessage("");
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(verifyPaymentResponse);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebhook(@RequestBody String payload,
			@RequestHeader("X-Razorpay-Signature") String signature) {
		System.out.println("signature--" + signature);

		try {
			boolean isValid = Utils.verifyWebhookSignature(payload, signature, razorpayWebhookSecret);

			if (!isValid) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
			}

			JSONObject event = new JSONObject(payload);
			String eventType = event.getString("event");

			if ("payment.captured".equals(eventType)) {
				JSONObject payment = event.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
				String paymentId = payment.getString("id");
				String orderId = payment.getString("order_id");
				paymentService.finalizePayment(orderId, paymentId);
			}

			return ResponseEntity.ok("OK");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
	}

}
