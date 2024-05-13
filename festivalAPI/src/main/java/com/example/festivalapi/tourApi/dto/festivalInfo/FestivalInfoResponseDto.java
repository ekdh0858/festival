package com.example.festivalapi.tourApi.dto.festivalInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FestivalInfoResponseDto {
    @JsonProperty("response")
    private FestivalInfoResponse response;

    private String depth = "1";
}
