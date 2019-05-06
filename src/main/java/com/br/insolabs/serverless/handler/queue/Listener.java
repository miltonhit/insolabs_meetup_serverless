package com.br.insolabs.serverless.handler.queue;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.br.insolabs.serverless.domains.QueueRecord;
import com.br.insolabs.serverless.domains.ServerlessQueueRequest;
import com.br.insolabs.serverless.domains.bussiness.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Listener {
	private ObjectMapper objectMapper;
	private AmazonSNS amazonSNS;
	
	public Listener() {
		this.objectMapper = new ObjectMapper();
		this.amazonSNS = AmazonSNSClientBuilder.defaultClient();
	}
	
	public void handleRequest(ServerlessQueueRequest request, Context context) throws Exception {
		
		//
		//
		try {
			
			for (QueueRecord queueRecord : request.getRecords()) {
				Message message = objectMapper.readValue(queueRecord.getBody(), Message.class);
				amazonSNS.publish(new PublishRequest().withTopicArn(System.getenv("topicArn")).withMessage(message.getText()));
			}
			
		} catch(Exception exc) {
			throw exc;
		}
	}
		
		
	// Testar
	//
	public static void main(String args[]) throws Exception {
		List<QueueRecord> records = new ArrayList<QueueRecord>();
		records.add(QueueRecord.builder().body("{\"text\":\"Teste 123\"}").build());

		//
		//
		ServerlessQueueRequest request = ServerlessQueueRequest.builder()
				.records(records)
				.build();
		//
		//
		new Listener().handleRequest(request, null);
    }
}
	
