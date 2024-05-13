package com.example.festivalapi.config.RestTemplate;

public class CustomApiException extends RuntimeException {
    public CustomApiException(String message) {
        super(message);
    }
}
