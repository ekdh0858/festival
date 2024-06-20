package com.example.festivalapi.dto.festival;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class FestivalListResDto {
    List<FestivalListDto> festivals;
    boolean isLast;
    boolean isFirst;

    public FestivalListResDto(Page<FestivalListDto> festivalPage) {
        this.festivals = festivalPage.getContent();
        this.isLast = festivalPage.isLast();
        this.isFirst = festivalPage.isFirst();
    }
}
