package com.example.festivalapi.config.RestTemplate;

import com.example.festivalapi.config.RestTemplate.CustomApiException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import java.io.IOException;
import java.util.Scanner;

public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            try (Scanner scanner = new Scanner(response.getBody()).useDelimiter("\\A")) {
                String responseBody = scanner.hasNext() ? scanner.next() : "";
                if (responseBody.contains("SERVICE_KEY_IS_NOT_REGISTERED_ERROR")) {
                    // 서비스 키가 등록되지 않은 오류가 발생하면 CustomApiException을 발생시킴
                    throw new CustomApiException("서비스 키가 등록되지 않았습니다.");
                }
            }
        }
        // 다른 오류는 기본 핸들러(DefaultResponseErrorHandler)를 사용하여 예외를 발생시킴
        super.handleError(response);
    }
}

