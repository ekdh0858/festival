package com.example.festivalapi.dto.tourApi;

import jakarta.annotation.Nullable;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class SearchFestivalResponseDto {
    private Response response;

    @Getter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    public static class Body {
        private Item items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    public static class Item {
        @Nullable
        private ArrayList<SearchedFestival> item;
    }

    @Getter
    public  static class SearchedFestival{
        private Long contentid;
        private String addr1;
        private String addr2;
        private String booktour;
        private String cat1;
        private String cat2;
        private String cat3;
        private String contenttypeid;
        private String createdtime;
        private String eventstartdate;
        private String eventenddate;
        private String firstimage;
        private String firstimage2;
        private String cpyrhtDivCd;
        private String mapx;
        private String mapy;
        private String mlevel;
        private String modifiedtime;
        private String areacode;
        private String sigungucode;
        private String tel;
        private String title;
    }


}
