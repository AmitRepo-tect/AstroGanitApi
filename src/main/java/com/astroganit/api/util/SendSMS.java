package com.astroganit.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.astroganit.api.model.OtpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SendSMS {
	@Value("${OTPKEYNEW}")
	private String OTPKEY;
	@Value("${OTPMSG}")
	private String OTPMSG;
	@Value("${OTPSENDER}")
	private String OTPSENDER;
	@Autowired
	private RestTemplate restTemplate;

	public String sendSms(String mobile, String otp) {
		try {
			String apiKey = "apikey=" + this.OTPKEY;
			String message = "&message=" + otp + " " + this.OTPMSG;
			String sender = "&sender=" + this.OTPSENDER;
			String numbers = "&numbers=" + mobile;
			HttpURLConnection conn = (HttpURLConnection) (new URL("https://api.textlocal.in/send/?")).openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();

			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}

			rd.close();
			return stringBuffer.toString();
		} catch (Exception var12) {
			System.out.println("Error SMS " + var12);
			return "Error " + var12;
		}
	}

	public String sendOtp(String mobile, String otpValue) {
		try {

			String apiKey = "your_api_key";
			String templateName = "template_name"; // if required
			String url = "https://2factor.in/API/V1/" + OTPKEY + "/SMS/" + mobile + "/" + otpValue;
			System.out.println("" + url);

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			String body = response.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			OtpResponse otpResponse = objectMapper.readValue(body, OtpResponse.class);

			return otpResponse.getStatus();

			// return "Success";
			/*
			 * // If template name required: // url = url + "/" + templateName; String json
			 * = restTemplate.getForObject(url, String.class); System.out.println("" +
			 * json);
			 * 
			 * ObjectMapper mapper = new ObjectMapper(); OtpResponse otpResponse = null; try
			 * { otpResponse = mapper.readValue(json, OtpResponse.class); } catch (Exception
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 * 
			 * OtpResponse otpResponse = restTemplate.getForObject(url, OtpResponse.class);
			 * return otpResponse.getStatus();
			 */
		} catch (Exception e) {
			return "Exception";
		}

	}
}
