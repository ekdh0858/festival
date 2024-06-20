package com.example.festivalapi.controller;

import com.example.festivalapi.service.TourApiService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourApiController {

    private final TourApiService tourApiService;


    @GetMapping("/update")
//  오늘 업데이트 된 축제 목록을 데이터베이스에 업데이트 시키는 API
    public ResponseEntity<String> getTourList(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String todayDate = formatter.format(date);

        try {
            tourApiService.updateTodayFestivals(todayDate);
        } catch (JSONException e) {
            String msg = e.getMessage();
            return  new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("업데이트가 완료되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/updateTest")
//  오늘 업데이트 된 축제 목록을 데이터베이스에 업데이트 시키는 API
    public ResponseEntity<String> updateList(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String todayDate = formatter.format(date);

        try {
            tourApiService.updateNewDatabase(todayDate);
        } catch (JSONException e) {
            String msg = e.getMessage();
            return  new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("업데이트가 완료되었습니다.", HttpStatus.OK);
    }
}
