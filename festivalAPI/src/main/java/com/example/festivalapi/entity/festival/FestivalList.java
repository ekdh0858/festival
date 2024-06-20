package com.example.festivalapi.entity.festival;

import com.example.festivalapi.dto.festival.FestivalReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "festival")
public class FestivalList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private long contentId;
    private String title;
    private String thumbNailImage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String venue;


    public FestivalList(FestivalReqDto reqDto) {
        this.contentId = reqDto.getContentId();
        this.title = reqDto.getTitle();
        this.thumbNailImage = reqDto.getThumbNailImage();
        this.startDate = LocalDate.parse(reqDto.getStartDate(), DateTimeFormatter.BASIC_ISO_DATE);
        this.endDate = LocalDate.parse(reqDto.getEndDate(), DateTimeFormatter.BASIC_ISO_DATE);
        this.venue = reqDto.getVenue();
    }

    public void update(FestivalReqDto reqDto) {
        this.title = reqDto.getTitle();
        this.thumbNailImage = reqDto.getThumbNailImage();
        this.startDate = LocalDate.parse(reqDto.getStartDate(), DateTimeFormatter.BASIC_ISO_DATE);
        this.endDate = LocalDate.parse(reqDto.getEndDate(), DateTimeFormatter.BASIC_ISO_DATE);
        this.venue = reqDto.getVenue();
    }
}


