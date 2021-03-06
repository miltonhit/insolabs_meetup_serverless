package com.br.insolabs.serverless.handler.queue;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.br.insolabs.serverless.domains.bussiness.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Listener {
	private ObjectMapper objectMapper;
	private AmazonSNS amazonSNS;
	
	public Listener() {
		this.objectMapper = new ObjectMapper();
		this.amazonSNS = AmazonSNSClientBuilder.defaultClient();
	}
	
	public void handleRequest(Object result, Context context) throws Exception {
		String jsonValue = result instanceof String ? (String) result : objectMapper.writeValueAsString(result);
		System.out.println(jsonValue);
		
		//
		//
		try {
			JSONObject jsonObject = new JSONObject(jsonValue); 
			JSONArray records = jsonObject.getJSONArray("Records"); 
			
			for (int x = 0; x < records.length(); x++) { 
				Message message= objectMapper.readValue(records.getJSONObject(x).getString("body"), Message.class);
				amazonSNS.publish(new PublishRequest().withTopicArn(System.getenv("topicArn")).withMessage(message.getText()));
			}
			
		} catch(Exception exc) {
			throw exc;
		}
	}
}		
		
//	// Testar
//	//
//	public static void main(String args[]) throws Exception {
//		List<QueueRecord> records = new ArrayList<QueueRecord>();
//		records.add(QueueRecord.builder().body("{\"text\":\"Teste 123\"}").build());
//		//
//		//
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("Records", records);
//		//
//		//
//		new Listener().handleRequest(jsonObject.toString(), null);
//    }
//}
	
