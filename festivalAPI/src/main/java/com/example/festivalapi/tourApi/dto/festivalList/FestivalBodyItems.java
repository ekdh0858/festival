package com.example.festivalapi.tourApi.dto.festivalList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FestivalBodyItems {
    @JsonProperty("items")
    private FestivalItem items;

    private String depth="3";

}
