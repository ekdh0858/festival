package com.example.festivalapi.entity.festival;

import com.example.festivalapi.dto.festival.FestivalReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class FestivalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long contentId;
    @Column(length = 500)
    private String homePage;
    private String tel;
    private String telName;
    @Column(length = 500)
    private String useTimeFestival;
    private String sponsor;
    private String playtime;



    public FestivalDetail(FestivalReqDto reqDto) {
       this.contentId = reqDto.getContentId();
       this.homePage = reqDto.getHomePage();
       this.tel = reqDto.getTel();
       this.telName = reqDto.getTelName();
       this.useTimeFestival = reqDto.getUseTimeFestival();
       this.sponsor = reqDto.getSponsor();
       this.playtime =reqDto.getPlaytime();
    }

    public void update(FestivalReqDto reqDto) {
        this.contentId = reqDto.getContentId();
        this.homePage = reqDto.getHomePage();
        this.tel = reqDto.getTel();
        this.telName = reqDto.getTelName();
        this.useTimeFestival = reqDto.getUseTimeFestival();
        this.sponsor = reqDto.getSponsor();
        this.playtime =reqDto.getPlaytime();
    }
}
