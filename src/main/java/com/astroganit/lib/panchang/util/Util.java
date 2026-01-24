package com.astroganit.lib.panchang.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.ResponseEntity;

public class Util {
	double[] y1 = { 7.0, 20.0, 6.0, 10.0, 7.0, 18.0, 16.0, 19.0, 17.0 };

	public static double fract(double x) {
		long i = (long) x;
		double y = x - (double) i;
		return y;
	}

	public static boolean verifySignature(
            String payload,
            String actualSignature,
            String secret
    ) {
        try {
            String generatedSignature = hmacSha256(payload, secret);
            return generatedSignature.equals(actualSignature);
        } catch (Exception e) {
            return false;
        }
    }
	public static  String hmacSha256(String data, String key) throws Exception {
		Mac mac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		mac.init(secretKey);
		byte[] hash = mac.doFinal(data.getBytes());
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
