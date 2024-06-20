package com.example.festivalapi.dto.tourApi;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DetailCommonResponseDto {
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
        private String title;
        private String createdtime;
        private String modifiedtime;
        private String tel;
        private String telname;
        private String homepage;
        private String booktour;
    }


}
