package com.example.festivalapi.tourApi.service;

import com.example.festivalapi.config.RestTemplate.CustomApiException;
import com.example.festivalapi.festival.Entity.Festival;
import com.example.festivalapi.festival.repository.FestivalRepository;
import com.example.festivalapi.tourApi.dto.festivalInfo.FestivalInfo;
import com.example.festivalapi.tourApi.dto.festivalInfo.FestivalInfoResponseDto;
import com.example.festivalapi.tourApi.dto.festivalList.FestivalResponseDto;
import com.example.festivalapi.tourApi.dto.festivalList.TourApiFestival;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TourApiService {

    private final RestTemplate restTemplate;
    private final FestivalRepository festivalRepository;

    @Value("${tour.api.key}")
    private String tourApiKey;

    public List<TourApiFestival> restTemplateTest(int numOfRows, int pageNo, String eventStartDate) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/searchFestival1")
                .queryParam("numOfRows",numOfRows)
                .queryParam("pageNo",pageNo)
                .queryParam("_type","json")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","web for test")
                .queryParam("eventStartDate",eventStartDate)
                .queryParam("serviceKey",tourApiKey)
                .encode()
                .build()
                .toUri();
        log.info(uri.toString());
        try {
            FestivalResponseDto response = restTemplate.getForObject(uri, FestivalResponseDto.class);

            // 정상적인 응답인 경우
            return response.getResponse().getBody().getItems().getItem();
        } catch (HttpClientErrorException e) {
            // HTTP 클라이언트 오류 처리
            log.error("HTTP 클라이언트 오류: {}", e.getRawStatusCode());
            throw new CustomApiException("HTTP 클라이언트 오류가 발생했습니다.");
        } catch (RestClientException e) {
            // 기타 RestTemplate 오류 처리
            log.error("RestTemplate 오류: {}", e.getMessage());
            throw new CustomApiException("RestTemplate 오류가 발생했습니다.");
        }
    }

    public FestivalInfo getFestivalInfo(int id) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/detailCommon1")
                .queryParam("contentId",id)
                .queryParam("_type","json")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","web for test")
                .queryParam("overviewYN","Y")
                .queryParam("serviceKey",tourApiKey)
                .encode()
                .build()
                .toUri();
        log.info(uri.toString());
        try {
            FestivalInfoResponseDto response = restTemplate.getForObject(uri, FestivalInfoResponseDto.class);

            // 정상적인 응답인 경우
            return response.getResponse().getBody().getItems().getItem().get(0);
        } catch (HttpClientErrorException e) {
            // HTTP 클라이언트 오류 처리
            log.error("HTTP 클라이언트 오류: {}", e.getRawStatusCode());
            throw new CustomApiException("HTTP 클라이언트 오류가 발생했습니다.");
        } catch (RestClientException e) {
            // 기타 RestTemplate 오류 처리
            log.error("RestTemplate 오류: {}", e.getMessage());
            throw new CustomApiException("RestTemplate 오류가 발생했습니다.");
        }
    }

    // 업데이트 될 것들 저장하기.
    public void saveNewFestivals() {
        List<Festival> festivals = festivalRepository.findAll();

        if(festivals.size() == 0 ){
            URI uri = UriComponentsBuilder
                    .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                    .path("/searchFestival1")
                    .queryParam("numOfRows",1)
                    .queryParam("pageNo",1)
                    .queryParam("_type","json")
                    .queryParam("MobileOS","ETC")
                    .queryParam("MobileApp","web for test")
                    .queryParam("eventStartDate","20240101")
                    .queryParam("serviceKey",tourApiKey)
                    .encode()
                    .build()
                    .toUri();
            log.info(uri.toString());
            try {
                FestivalResponseDto response = restTemplate.getForObject(uri, FestivalResponseDto.class);

                // 정상적인 응답인 경우
                int totalCount = response.getResponse().getBody().getTotalCount();
                URI newUri = UriComponentsBuilder
                        .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                        .path("/searchFestival1")
                        .queryParam("numOfRows",totalCount)
                        .queryParam("pageNo",1)
                        .queryParam("_type","json")
                        .queryParam("MobileOS","ETC")
                        .queryParam("MobileApp","web for test")
                        .queryParam("eventStartDate","20240101")
                        .queryParam("serviceKey",tourApiKey)
                        .encode()
                        .build()
                        .toUri();
                FestivalResponseDto newResponse = restTemplate.getForObject(newUri,FestivalResponseDto.class);
                List<TourApiFestival> festivalList = newResponse.getResponse().getBody().getItems().getItem();
//                festivalList.stream().map()
            } catch (HttpClientErrorException e) {
                // HTTP 클라이언트 오류 처리
                log.error("HTTP 클라이언트 오류: {}", e.getRawStatusCode());
                throw new CustomApiException("HTTP 클라이언트 오류가 발생했습니다.");
            } catch (RestClientException e) {
                // 기타 RestTemplate 오류 처리
                log.error("RestTemplate 오류: {}", e.getMessage());
                throw new CustomApiException("RestTemplate 오류가 발생했습니다.");
            }
        }
    }
}

