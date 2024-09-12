package com.fiap.tech_challenge_5.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
public class AuthRestClientGateway {

    private final RestClient restClient;

    public AuthRestClientGateway() {
        this.restClient = RestClient.create();
    }

    public boolean validateToken(String token, UUID userId) {
        ResponseEntity<String> responseEntity = this.restClient
                .get()
                .uri("http://user:8083/auth/validate-token/{token}/{userId}", token, userId.toString())
                .retrieve()
                .toEntity(String.class);
        return responseEntity.getStatusCode() == HttpStatus.OK && "Valid token".equals(responseEntity.getBody());
    }

}
