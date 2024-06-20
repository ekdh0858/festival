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
public class FestivalIntro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long contentId;
    @Column(columnDefinition = "TEXT")
    private String overview;
    @Column(columnDefinition = "TEXT")
    private String infoText;

    public FestivalIntro(FestivalReqDto reqDto) {
        this.contentId = reqDto.getContentId();
        this.overview = reqDto.getOverview();
        this.infoText = reqDto.getInfoText();
    }

    public void update(FestivalReqDto reqDto) {
        this.contentId = reqDto.getContentId();
        this.overview = reqDto.getOverview();
        this.infoText = reqDto.getInfoText();
    }
}
