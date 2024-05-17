package com.example.festivalapi.festival.Dto;

import com.example.festivalapi.festival.Entity.Festival;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class FestivalResponseDto {
    private String title;
    private String thumbnail_image;
    private LocalDate event_start_date;
    private LocalDate event_end_date;
    private String organizer;

    public FestivalResponseDto(Festival saveFestival) {
        this.title = saveFestival.getTitle();
        this.thumbnail_image = saveFestival.getThumbnail_image();
        this.event_start_date = saveFestival.getEvent_start_date();
        this.event_end_date = saveFestival.getEvent_end_date();
        this.organizer = saveFestival.getOrganizer();
    }
}
