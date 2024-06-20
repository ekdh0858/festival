package com.example.festivalapi.dto.tourApi;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DetailIntroResponseDto {
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
        private String sponsor1;
        private String sponsor1tel;
        private String sponsor2;
        private String sponsor2tel;
        private String eventenddate;
        private String playtime;
        private String eventplace;
        private String eventhomepage;
        private String agelimit;
        private String bookingplace;
        private String placeinfo;
        private String subevent;
        private String program;
        private String eventstartdate;
        private String usetimefestival;
        private String discountinfofestival;
        private String spendtimefestival;
        private String festivalgrade;
    }


}
