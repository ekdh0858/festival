package com.example.festivalapi.schedule;

import com.example.festivalapi.service.TourApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final TourApiService tourApiService;

    @Scheduled(cron = "0 0 2 * * *", zone = "Asia/Seoul")
    public void upadteTodayFestivals() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String todayDate = formatter.format(date);

        tourApiService.updateTodayFestivals(todayDate);

    }

}
