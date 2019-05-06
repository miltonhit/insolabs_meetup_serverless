package com.br.insolabs.serverless.domains;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author milton
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerlessApiResponse {
	private boolean isBase64Encoded;
	private int statusCode;
	private String body;
	
	public Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Access-Control-Allow-Origin", "*");
		headers.put("Content-Type", "application/json;charset=UTF-8");
		return headers;
	}
}