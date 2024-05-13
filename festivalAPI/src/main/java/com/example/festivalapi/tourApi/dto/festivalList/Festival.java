package com.example.festivalapi.tourApi.dto.festivalList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming
public class Festival {
    private long contentid;
    private String title;
    private String firstimage;
    private String eventstartdate;
    private String eventenddate;
    private String addr1;
    private String modifiedtime;
}
