package com.example.festivalapi.tourApi.dto.festivalList;

import com.example.festivalapi.tourApi.dto.festivalList.FestivalBodyItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class FestivalResponseBody {
    @JsonProperty("body")
    private FestivalBodyItems body;

    private String depth = "2";
}
