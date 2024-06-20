package com.example.festivalapi.dto.tourApi;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DetailInfoResponseDto {
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
        private ArrayList<SearchedFestival> item;
    }

    @Getter
    public  static class SearchedFestival{
        private Long contentid;
        private String contenttypeid;
        private String serialnum;
        private String infoname;
        private String infotext;
        private String fldgubun;
    }


}
