package com.postgres.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NodeRedClient {

    @Value("${node-red.url}")
    private String nodeRedUrl;

    private final RestTemplate restTemplate = new RestTemplate();

            public void sendToNodeRed(Object data){
        String endpoint = nodeRedUrl + "/endpoint";
        restTemplate.postForEntity(endpoint, data, String.class);
            }
}
