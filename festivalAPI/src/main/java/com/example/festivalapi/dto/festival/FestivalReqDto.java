package com.example.festivalapi.dto.festival;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class FestivalReqDto {
    @NotNull
    private long contentId;
    @NotNull
    private  String title;
    private String thumbNailImage;
    @NotNull
    private  String startDate;
    @NotNull
    private  String endDate;
    @NotNull
    private  String venue;
    @Size(max = 500)
    private String homePage;
    private String tel;
    private String telName;
    @Size(max = 500)
    private String useTimeFestival;
    private String sponsor;
    private String playtime;

    private String overview;
    private String infoText;

}

