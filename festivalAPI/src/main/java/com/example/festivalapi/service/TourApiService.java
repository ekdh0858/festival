package com.example.festivalapi.service;

import com.example.festivalapi.dto.tourApi.DetailCommonResponseDto;
import com.example.festivalapi.dto.tourApi.DetailInfoResponseDto;
import com.example.festivalapi.dto.tourApi.DetailIntroResponseDto;
import com.example.festivalapi.dto.tourApi.SearchFestivalResponseDto;
import com.example.festivalapi.entity.festival.FestivalDetail;
import com.example.festivalapi.entity.festival.FestivalIntro;
import com.example.festivalapi.entity.festival.FestivalList;
import com.example.festivalapi.repository.DetailRepository;
import com.example.festivalapi.repository.FestivalRepository;
import com.example.festivalapi.repository.IntroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "TourApiService")
public class TourApiService {

    @Value("${tour.api.key}")
    private String secretKey;

    private final  RestTemplate restTemplate;
    private final  FestivalRepository festivalRepository;
    private final DetailRepository detailRepository;
    private final IntroRepository introRepository;

    @Transactional
    public void updateTodayFestivals(String todayDate){
        URI testUri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/searchFestival1")
                .queryParam("_type","json")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","web_for_test")
                .queryParam("eventStartDate","20240101")
                .queryParam("modifiedtime",todayDate)
                .queryParam("serviceKey",secretKey)
                .build(true)
                .toUri();

        SearchFestivalResponseDto res = restTemplate.getForObject(testUri, SearchFestivalResponseDto.class);
        int total = res.getResponse().getBody().getTotalCount();
        if(total ==0){
            return;
        }
        URI lastUri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/searchFestival1")
                .queryParam("_type","json")
                .queryParam("MobileOS","ETC")
                .queryParam("numOfRows",total)
                .queryParam("MobileApp","web_for_test")
                .queryParam("eventStartDate","20240101")
                .queryParam("modifiedtime","20240101")
                .queryParam("serviceKey",secretKey)
                .build(true)
                .toUri();
        SearchFestivalResponseDto response = restTemplate.getForObject(lastUri, SearchFestivalResponseDto.class);
        ArrayList<SearchFestivalResponseDto.SearchedFestival> item = response.getResponse().getBody().getItems().getItem();
        for(int i=0;i<item.size();i++){
            SearchFestivalResponseDto.SearchedFestival searchedFestival = item.get(i);
            Long contentId = searchedFestival.getContentid();

            Boolean isExist = festivalRepository.findByContentId(contentId).isPresent();

            FestivalList festivalList = isExist?
                    festivalRepository.findByContentId(contentId)
                            .orElseThrow(() -> new RuntimeException("Festival not found"))
                    :new FestivalList();




            URI detailUri = UriComponentsBuilder
                    .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                    .path("/detailCommon1")
                    .queryParam("_type","json")
                    .queryParam("MobileOS","ETC")
                    .queryParam("contentId",contentId)
                    .queryParam("defaultYN","Y")
                    .queryParam("MobileApp","web_for_test")
                    .queryParam("serviceKey",secretKey)
                    .build(true)
                    .toUri();

            DetailCommonResponseDto detailResult = restTemplate.getForObject(detailUri, DetailCommonResponseDto.class);
            DetailCommonResponseDto.SearchedFestival searchedDetail = detailResult.getResponse().getBody().getItems().getItem().get(0);
            Boolean detailIsExist = detailRepository.findByContentId(contentId).isPresent();

            FestivalDetail festivalDetail = detailIsExist?
                    detailRepository.findByContentId(contentId)
                            .orElseThrow(() -> new RuntimeException("FestivalDetail not found"))
                    :new FestivalDetail();


            URI introUri = UriComponentsBuilder
                    .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                    .path("/detailIntro1")
                    .queryParam("_type","json")
                    .queryParam("MobileOS","ETC")
                    .queryParam("contentId",contentId)
                    .queryParam("contentTypeId",15)
                    .queryParam("MobileApp","web_for_test")
                    .queryParam("serviceKey",secretKey)
                    .build(true)
                    .toUri();

            DetailIntroResponseDto introResult = restTemplate.getForObject(introUri, DetailIntroResponseDto.class);
            DetailIntroResponseDto.SearchedFestival searchedIntro = introResult.getResponse().getBody().getItems().getItem().get(0);


            URI infoUri = UriComponentsBuilder
                    .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                    .path("/detailInfo1")
                    .queryParam("_type","json")
                    .queryParam("MobileOS","ETC")
                    .queryParam("contentId",contentId)
                    .queryParam("contentTypeId",15)
                    .queryParam("MobileApp","web_for_test")
                    .queryParam("serviceKey",secretKey)
                    .build(true)
                    .toUri();


            DetailInfoResponseDto infoResult = restTemplate.getForObject(infoUri, DetailInfoResponseDto.class);
            DetailInfoResponseDto.Item searchedItem = infoResult.getResponse().getBody().getItems();
            log.info("info :",searchedItem.getItem().get(0).getContentid());

            String overview = searchedItem.getItem().isEmpty() ? "소개글이 없습니다.":searchedItem.getItem().get(0).getInfotext();
            String infoText = searchedItem.getItem().size()> 1  ? searchedItem.getItem().get(1).getInfotext():"소개글이 없습니다.";

            Boolean introIsExist = detailRepository.findByContentId(contentId).isPresent();

            FestivalIntro festivalIntro = introIsExist?
                    introRepository.findByContentId(contentId)
                            .orElseThrow(() -> new RuntimeException("FestivalIntro not found"))
                    :new FestivalIntro();

            festivalList.setContentId(searchedFestival.getContentid());
            festivalList.setTitle(searchedFestival.getTitle());
            festivalList.setThumbNailImage(searchedFestival.getFirstimage());
            festivalList.setStartDate(LocalDate.parse(searchedFestival.getEventstartdate(), DateTimeFormatter.BASIC_ISO_DATE));
            festivalList.setEndDate(LocalDate.parse(searchedFestival.getEventenddate(), DateTimeFormatter.BASIC_ISO_DATE));
            festivalList.setVenue(searchedIntro.getEventplace());
            festivalRepository.save(festivalList);

            festivalDetail.setContentId(searchedDetail.getContentid());
            festivalDetail.setHomePage(searchedDetail.getHomepage());
            festivalDetail.setTel(searchedDetail.getTel());
            festivalDetail.setTelName(searchedDetail.getTelname());
            festivalDetail.setUseTimeFestival(searchedIntro.getUsetimefestival());
            festivalDetail.setSponsor(searchedIntro.getSponsor1());
            festivalDetail.setPlaytime(searchedIntro.getPlaytime());
            detailRepository.save(festivalDetail);

            festivalIntro.setContentId(contentId);
            festivalIntro.setOverview(overview);
            festivalIntro.setInfoText(infoText);
            introRepository.save(festivalIntro);

        }

    }


    @Transactional
    public void updateNewDatabase(String todayDate){
        URI testUri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/searchFestival1")
                .queryParam("_type","json")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","web_for_test")
                .queryParam("eventStartDate","20240101")
                .queryParam("serviceKey",secretKey)
                .build(true)
                .toUri();
        SearchFestivalResponseDto res = restTemplate.getForObject(testUri, SearchFestivalResponseDto.class);
        int total = res.getResponse().getBody().getTotalCount();

        URI lastUri = UriComponentsBuilder
                .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                .path("/searchFestival1")
                .queryParam("_type","json")
                .queryParam("MobileOS","ETC")
                .queryParam("numOfRows",total)
                .queryParam("MobileApp","web_for_test")
                .queryParam("eventStartDate","20240101")
//                .queryParam("modifiedtime","20240101")
                .queryParam("serviceKey",secretKey)
                .build(true)
                .toUri();
        SearchFestivalResponseDto response = restTemplate.getForObject(lastUri, SearchFestivalResponseDto.class);
        ArrayList<SearchFestivalResponseDto.SearchedFestival> item = response.getResponse().getBody().getItems().getItem();
        for(int i=0;i<item.size();i++){
            SearchFestivalResponseDto.SearchedFestival searchedFestival = item.get(i);
            Long contentId = searchedFestival.getContentid();

            Boolean isExist = festivalRepository.findByContentId(contentId).isPresent();

            FestivalList festivalList = isExist?
                    festivalRepository.findByContentId(contentId)
                            .orElseThrow(() -> new RuntimeException("Festival not found"))
                    :new FestivalList();




            URI detailUri = UriComponentsBuilder
                    .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                    .path("/detailCommon1")
                    .queryParam("_type","json")
                    .queryParam("MobileOS","ETC")
                    .queryParam("contentId",contentId)
                    .queryParam("defaultYN","Y")
                    .queryParam("MobileApp","web_for_test")
                    .queryParam("serviceKey",secretKey)
                    .build(true)
                    .toUri();

            DetailCommonResponseDto detailResult = restTemplate.getForObject(detailUri, DetailCommonResponseDto.class);
            DetailCommonResponseDto.SearchedFestival searchedDetail = detailResult.getResponse().getBody().getItems().getItem().get(0);
            Boolean detailIsExist = detailRepository.findByContentId(contentId).isPresent();

            FestivalDetail festivalDetail = detailIsExist?
                    detailRepository.findByContentId(contentId)
                            .orElseThrow(() -> new RuntimeException("FestivalDetail not found"))
                    :new FestivalDetail();


            URI introUri = UriComponentsBuilder
                    .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                    .path("/detailIntro1")
                    .queryParam("_type","json")
                    .queryParam("MobileOS","ETC")
                    .queryParam("contentId",contentId)
                    .queryParam("contentTypeId",15)
                    .queryParam("MobileApp","web_for_test")
                    .queryParam("serviceKey",secretKey)
                    .build(true)
                    .toUri();

            DetailIntroResponseDto introResult = restTemplate.getForObject(introUri, DetailIntroResponseDto.class);
            DetailIntroResponseDto.SearchedFestival searchedIntro = introResult.getResponse().getBody().getItems().getItem().get(0);


            URI infoUri = UriComponentsBuilder
                    .fromUriString("http://apis.data.go.kr/B551011/KorService1")
                    .path("/detailInfo1")
                    .queryParam("_type","json")
                    .queryParam("MobileOS","ETC")
                    .queryParam("contentId",contentId)
                    .queryParam("contentTypeId",15)
                    .queryParam("MobileApp","web_for_test")
                    .queryParam("serviceKey",secretKey)
                    .build(true)
                    .toUri();

            DetailInfoResponseDto infoResult = restTemplate.getForObject(infoUri, DetailInfoResponseDto.class);
            DetailInfoResponseDto.Item searchedItem = infoResult.getResponse().getBody().getItems();

            log.info("info : {} ",searchedItem.getItem().get(0).getContentid());



            String overview = searchedItem.getItem().isEmpty() ? "소개글이 없습니다.":searchedItem.getItem().get(0).getInfotext();
            String infoText = searchedItem.getItem().size()> 1  ? searchedItem.getItem().get(1).getInfotext():"소개글이 없습니다.";

            Boolean introIsExist = detailRepository.findByContentId(contentId).isPresent();

            FestivalIntro festivalIntro = introIsExist?
                    introRepository.findByContentId(contentId)
                            .orElseThrow(() -> new RuntimeException("FestivalIntro not found"))
                    :new FestivalIntro();

            festivalList.setContentId(searchedFestival.getContentid());
            festivalList.setTitle(searchedFestival.getTitle());
            festivalList.setThumbNailImage(searchedFestival.getFirstimage());
            festivalList.setStartDate(LocalDate.parse(searchedFestival.getEventstartdate(), DateTimeFormatter.BASIC_ISO_DATE));
            festivalList.setEndDate(LocalDate.parse(searchedFestival.getEventenddate(), DateTimeFormatter.BASIC_ISO_DATE));
            festivalList.setVenue(searchedIntro.getEventplace());
            festivalRepository.save(festivalList);

            festivalDetail.setContentId(searchedDetail.getContentid());
            festivalDetail.setHomePage(searchedDetail.getHomepage());
            festivalDetail.setTel(searchedDetail.getTel());
            festivalDetail.setTelName(searchedDetail.getTelname());
            festivalDetail.setUseTimeFestival(searchedIntro.getUsetimefestival());
            festivalDetail.setSponsor(searchedIntro.getSponsor1());
            festivalDetail.setPlaytime(searchedIntro.getPlaytime());
            detailRepository.save(festivalDetail);

            festivalIntro.setContentId(contentId);
            festivalIntro.setOverview(overview);
            festivalIntro.setInfoText(infoText);
            introRepository.save(festivalIntro);

        }

    }

}

