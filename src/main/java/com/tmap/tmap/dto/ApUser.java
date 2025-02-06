package com.tmap.tmap.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApUser {


    private String userCode = "";
    private String userId = "";
    private String userPw = "";
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String homeAddr1;
    private String homeAddr2;
    private String subAddr1;
    private String subAddr2;
    private String home1LocX; // 경도
    private String home1LocY; // 위도
    private String home2LocX;
    private String home2LocY;
    private String sub1LocX;
    private String sub1LocY;
    private String sub2LocX;
    private String sub2LocY;

}
