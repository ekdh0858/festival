package com.example.festivalapi.festival.Controller;

import com.example.festivalapi.repository.FestivalRepository;
import com.example.festivalapi.service.TourApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class FestivalControllerTest {
    @Autowired
    TourApiService tourApiService;

    @Autowired
    private FestivalRepository festivalRepository;

    @Test
    @DisplayName("update테스트")
    public void updateTest() {
        tourApiService.updateTodayFestivals("20240101");

        int count = festivalRepository.findAll().size();
        System.out.println(count);
    }
}