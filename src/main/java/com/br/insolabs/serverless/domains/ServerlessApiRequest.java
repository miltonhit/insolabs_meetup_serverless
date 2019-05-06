package com.br.insolabs.serverless.domains;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerlessApiRequest {
    private String resource;
    private String path;
    private String httpMethod;
    private Map<String, String> headers;
    private Map<String, String> queryStringParameters;
    private Map<String, String> pathParameters;
    private Map<String, String> stageVariables;
    private String body;
    private boolean isBase64Encoded;
    
    public Map<String, String> getQueryStringParameters() {
    	if (this.queryStringParameters == null) this.queryStringParameters = new HashMap<String, String>();
    	return this.queryStringParameters;
    }
    
    public Map<String, String> getPathParameters() {
    	if (this.pathParameters == null) this.pathParameters = new HashMap<String, String>();
    	return this.pathParameters;
    }
}
