package com.br.insolabs.serverless.domains;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerlessQueueRequest {
	private List<QueueRecord> Records;
}
