package com.example.festivalapi.festival.Dto;

import lombok.Getter;

@Getter
public class FestivalRequestDto {
    private String title;
    private String thumbnail_image;
    private String event_start_date;
    private String event_end_date;
    private String organizer;
}
