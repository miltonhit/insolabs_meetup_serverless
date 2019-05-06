package com.br.insolabs.serverless.handler.api;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.br.insolabs.serverless.domains.ServerlessApiRequest;
import com.br.insolabs.serverless.domains.ServerlessApiResponse;
import com.br.insolabs.serverless.domains.bussiness.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SaveCellphone {
	private ObjectMapper objectMapper;
	private AmazonSNS amazonSNS;
	
	public SaveCellphone() {
		this.objectMapper = new ObjectMapper();
		this.amazonSNS = AmazonSNSClientBuilder.defaultClient();
	}
	
	// Lambda
	// Extrair informações do request
	public ServerlessApiResponse handleRequest(ServerlessApiRequest request, Context context) throws IOException {
		System.out.println(objectMapper.writeValueAsString(request));
		ServerlessApiResponse response = null;
		
		//
		//
		try {
			Client client = objectMapper.readValue(request.getBody(), Client.class);
			
			//
			//
			SubscribeResult subscribeResult = amazonSNS.subscribe(new SubscribeRequest()
					.withTopicArn(System.getenv("topicArn"))
					.withProtocol("SMS")
					.withEndpoint("+55" + client.getCellphone()));
			client.setId(subscribeResult.getSubscriptionArn());
			
			//
			//
			response = ServerlessApiResponse.builder()
					.statusCode(200)
					.body(objectMapper.writeValueAsString(client))
					.build();
			
		} catch(Exception exc) {
			ServerlessApiResponse.builder()
				.statusCode(500)
				.body(exc.getMessage())
				.build();
		}
		
		//
		//
		return response;
	}
		
		
	// Testar
	//
	public static void main(String args[]) throws Exception {
		ServerlessApiRequest request = ServerlessApiRequest.builder()
			.body("{\"cellphone\": \"+55987702333\"}")
			.build();
		
		//
		//
		ServerlessApiResponse response = new SaveCellphone().handleRequest(request, null);
 		System.out.print(response); 		
    }
}
	
