package com.example.festivalapi.dto.festival;

import com.example.festivalapi.entity.festival.FestivalList;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FestivalListDto {
private long id;
private long contentId;
private String title;
private String firstImage;
private LocalDate startDate;
private LocalDate endDate;
private String venue;
private Status status;

    public FestivalListDto(FestivalList festivalList) {
        this.id = festivalList.getId();
        this.contentId = festivalList.getContentId();
        this.title = festivalList.getTitle();
        this.firstImage = festivalList.getThumbNailImage();
        this.startDate = festivalList.getStartDate();
        this.endDate = festivalList.getEndDate();
        this.venue = festivalList.getVenue();
        this.status = calculateStatus();

    }

    private Status calculateStatus() {
        LocalDate today = LocalDate.now();
        if (today.isBefore(startDate)) {
            return Status.UPCOMING;
        } else if (today.isEqual(startDate) || today.isBefore(endDate)) {
            return Status.BEING;
        } else {
            return Status.ENDED;
        }
    }

    enum Status{
        BEING,UPCOMING,ENDED
    };
}



