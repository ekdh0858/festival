package com.example.festivalapi.tourApi.dto.festivalInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@JsonNaming
public class FestivalInfo {
    private long contentid;
    private String overview;
}
