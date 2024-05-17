package com.example.festivalapi.tourApi.dto.festivalList;

import com.example.festivalapi.tourApi.dto.festivalList.FestivalResponseBody;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FestivalResponseDto {
    @JsonProperty("response")
    private FestivalResponseBody response;

    private String depth = "1";
}

