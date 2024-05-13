package com.example.festivalapi.tourApi.controller;

import com.example.festivalapi.tourApi.dto.festivalList.Festival;
import com.example.festivalapi.tourApi.dto.festivalInfo.FestivalInfo;
import com.example.festivalapi.tourApi.service.TourApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/festivals")
@RequiredArgsConstructor
public class TourApiController {

    private final TourApiService tourApiService;
    @GetMapping("/list")
    // 홈페이지에서 보여줄 축제 리스트
    public List<Festival> getFestivals(
            @RequestParam int numOfRows,        // 몇개씩 가져올 건지
            @RequestParam int pageNo,           // 몇 번째 페이지의 데이터를 가져올지
            @RequestParam String eventStartDate // 검색할 축제들의 시작일
    ){
        return tourApiService.restTemplateTest(numOfRows,pageNo,eventStartDate);
    }

    @GetMapping("/festivalInfo/{id}")
    // 축제 상세보기에서 축제 개요 정보
    // id에 축제의 contentid를 준다.
    public FestivalInfo getFesitivalInfo(@PathVariable int id){
        return tourApiService.getFestivalInfo(id);

    }

}
