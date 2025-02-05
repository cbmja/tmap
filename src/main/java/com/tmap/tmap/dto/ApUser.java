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


}
