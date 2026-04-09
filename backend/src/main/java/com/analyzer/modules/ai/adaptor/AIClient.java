package com.analyzer.modules.ai.adaptor;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AIClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String detectAnomalies(Object request) {
        String url = "http://localhost:8000/detect-anomalies";
        return restTemplate.postForObject(url, request, String.class);
    }

}
