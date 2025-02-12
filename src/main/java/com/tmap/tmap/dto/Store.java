package com.tmap.tmap.dto;

import lombok.Data;

@Data
public class Store {

    private String err;
    // 상가업소번호
    private String merchantNum;

    // 상호명
    private String storeNm;

    // 지점명
    private String branchNm;

    // 상권업종대분류코드
    private String businessCategoryNum;

    // 상권업종대분류명
    private String businessCategoryNm;

    // 상권업종중분류코드
    private String businessSubcategoryNum;

    // 상권업종중분류명
    private String businessSubcategoryNm;

    // 상권업종소분류코드
    private String businessSubsubcategoryNum;

    // 상권업종소분류명
    private String businessSubsubcategoryNm;

    // 표준산업분류코드
    private String standardIndustryNum;

    // 표준산업분류명
    private String standardIndustryNm;

    // 시도코드
    private String provinceNum;

    // 시도명
    private String provinceNm;

    // 시군구코드
    private String cityNum;

    // 시군구명
    private String cityNm;

    // 행정동코드
    private String administrativeDistrictNum;

    // 행정동명
    private String administrativeDistrictNm;

    // 법정동코드
    private String legalDistrictNum;

    // 법정동명
    private String legalDistrictNm;

    // 지번코드
    private String lotNum;

    // 대지구분코드
    private String landClassificationNum;

    // 대지구분명
    private String landClassificationNm;

    // 지번본번지
    private String lotMainNum;

    // 지번부번지
    private String lotSubNum;

    // 지번주소
    private String lotAdd;

    // 도로명코드
    private String roadNameNum;

    // 도로명
    private String roadNm;

    // 건물본번지
    private String buildingMainNum;

    // 건물부번지
    private String buildingSubNum;

    // 건물관리번호
    private String buildingManagementNum;

    // 건물명
    private String buildingNm;

    // 도로명주소
    private String roadNameAdd;

    // 구우편번호
    private String oldPostalNum;

    // 신우편번호
    private String newPostalNum;

    // 동정보
    private String neighborhoodInfo;

    // 층정보
    private String floorInfo;

    // 호정보
    private String unitInfo;

    // 경도
    private String longitude;

    // 위도
    private String latitude;

    private String event;
}
