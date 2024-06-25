package com.example.festivalapi.dto.festival;

import com.example.festivalapi.entity.festival.FestivalDetail;
import com.example.festivalapi.entity.festival.FestivalIntro;
import com.example.festivalapi.entity.festival.FestivalList;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class FestivalByContentIdDto {
    long contentId;
    String homePage,
    tel,
    telName,
    useTimeFestival,
    sponsor,
    playtime,
    overview,
    infoText,
    title,
    thumbNailImage,
    venue;
    LocalDate startDate;
    LocalDate endDate;

    public FestivalByContentIdDto(FestivalList festivalList, FestivalIntro festivalIntro, FestivalDetail festivalDetail) {
        this.contentId = festivalList.getContentId();
        this.homePage = festivalDetail.getHomePage();
        this.tel = festivalDetail.getTel();
        this.telName = festivalDetail.getTelName();
        this.useTimeFestival = festivalDetail.getUseTimeFestival();
        this.sponsor = festivalDetail.getSponsor();
        this.playtime = festivalDetail.getPlaytime();
        this.overview = festivalIntro.getOverview();
        this.infoText = festivalIntro.getInfoText();
        this.title = festivalList.getTitle();
        this.thumbNailImage = festivalList.getThumbNailImage();
        this.venue = festivalList.getVenue();
        this.startDate = festivalList.getStartDate();
        this.endDate = festivalList.getEndDate();
    }



}
