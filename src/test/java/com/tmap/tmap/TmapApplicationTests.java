package com.tmap.tmap;

import com.tmap.tmap.dto.Store;
import com.tmap.tmap.service.StoreService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
class TmapApplicationTests {

	@Autowired
	private StoreService storeService;

	@Test
	void contextLoads() {
		// 테스트용 CSV 파일 경로
		String filePath = "C:\\Users\\jeon\\Desktop\\배달\\상가정보\\소상공인시장진흥공단_상가(상권)정보_20241231\\1서울.csv";

		try {
			// 시작 시간 기록
			long startTime = System.currentTimeMillis();

			// CSVParser를 사용하여 파일 읽기
			CSVParser csvParser = new CSVParser(
					Files.newBufferedReader(Paths.get(filePath)),
					CSVFormat.DEFAULT.withFirstRecordAsHeader() // 첫 번째 행을 헤더로 사용
			);

			// 헤더 출력
			System.out.println("Headers: " + csvParser.getHeaderNames());

			int i = 0;
			// 레코드 읽기
			for (CSVRecord record : csvParser) {
				// Store 객체 생성 및 매핑
				Store store = new Store();
				store.setMerchantNum(record.get("상가업소번호"));
				store.setStoreNm(record.get("상호명"));
				store.setBranchNm(record.get("지점명"));
				store.setBusinessCategoryNum(record.get("상권업종대분류코드"));
				store.setBusinessCategoryNm(record.get("상권업종대분류명"));
				store.setBusinessSubcategoryNum(record.get("상권업종중분류코드"));
				store.setBusinessSubcategoryNm(record.get("상권업종중분류명"));
				store.setBusinessSubsubcategoryNum(record.get("상권업종소분류코드"));
				store.setBusinessSubsubcategoryNm(record.get("상권업종소분류명"));
				store.setStandardIndustryNum(record.get("표준산업분류코드"));
				store.setStandardIndustryNm(record.get("표준산업분류명"));
				store.setProvinceNum(record.get("시도코드"));
				store.setProvinceNm(record.get("시도명"));
				store.setCityNum(record.get("시군구코드"));
				store.setCityNm(record.get("시군구명"));
				store.setAdministrativeDistrictNum(record.get("행정동코드"));
				store.setAdministrativeDistrictNm(record.get("행정동명"));
				store.setLegalDistrictNum(record.get("법정동코드"));
				store.setLegalDistrictNm(record.get("법정동명"));
				store.setLotNum(record.get("지번코드"));
				store.setLandClassificationNum(record.get("대지구분코드"));
				store.setLandClassificationNm(record.get("대지구분명"));
				store.setLotMainNum(record.get("지번본번지"));
				store.setLotSubNum(record.get("지번부번지"));
				store.setLotAdd(record.get("지번주소"));
				store.setRoadNameNum(record.get("도로명코드"));
				store.setRoadNm(record.get("도로명"));
				store.setBuildingMainNum(record.get("건물본번지"));
				store.setBuildingSubNum(record.get("건물부번지"));
				store.setBuildingManagementNum(record.get("건물관리번호"));
				store.setBuildingNm(record.get("건물명"));
				store.setRoadNameAdd(record.get("도로명주소"));
				store.setOldPostalNum(record.get("구우편번호"));
				store.setNewPostalNum(record.get("신우편번호"));
				store.setNeighborhoodInfo(record.get("동정보"));
				store.setFloorInfo(record.get("층정보"));
				store.setUnitInfo(record.get("호정보"));
				store.setLongitude(record.get("경도"));
				store.setLatitude(record.get("위도"));

				// Store 객체 저장
				storeService.save(store);

                /*
                // Store 객체 출력
                System.out.println(store);
                System.out.println("========");

                // 최대 100개까지만 처리
                i++;
                if (i == 100) {
                    break;
                }
                */
				if(i%1000 == 0){
					System.out.println(i);
				}

			}

			// CSVParser 닫기
			csvParser.close();

			// 종료 시간 기록 및 소요 시간 계산
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;

			// 소요 시간 출력
			System.out.println("작업 완료! 소요 시간: " + elapsedTime + "ms");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
