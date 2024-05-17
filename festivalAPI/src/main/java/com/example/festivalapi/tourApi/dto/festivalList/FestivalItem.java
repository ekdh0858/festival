package com.example.festivalapi.tourApi.dto.festivalList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class FestivalItem {
    @JsonProperty("item")
    private List<TourApiFestival> item;

    private String depth = "4";
}
