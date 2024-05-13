package com.example.festivalapi.tourApi.dto.festivalInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FestivalInfoItems {
    @JsonProperty("item")
    private List<FestivalInfo> item;
}
