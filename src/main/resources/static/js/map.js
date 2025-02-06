//https://apis.openapi.sk.com/tmap/app/routes?
//appKey=vvDaWb0OKf47bffQbFWVBa2dmAdHsdS61P7wjixS
//&goalname=SKT타워
//&startx=126.984098 출발경도
//&starty=37.566385 출발위도
//&startname=회사 출발지명
//&goalx= 목적지 경도
//&goaly=목적지 위도
//&goalname=집 목적지명



$(document).on('click', '.addr-btn', function(){

    let type = $(this).data('type');
    console.log(type);
    let addr = $('#'+type+'-addr').val();
    // alert(addr);


    var fullAddr = addr;
    var headers = {};
    headers["appKey"]="vvDaWb0OKf47bffQbFWVBa2dmAdHsdS61P7wjixS";

    // 좌표값 가져오기
    $.ajax({
        method : "GET",
        headers : headers,
        url : "https://apis.openapi.sk.com/tmap/geo/fullAddrGeo?version=1&format=json&callback=result",
        async : false,
        data : {
            "coordType" : "WGS84GEO",
            "fullAddr" : fullAddr
        },
        success : function(response) {

            var resultInfo = response.coordinateInfo; // .coordinate[0];

            if (resultInfo.coordinate.length == 0) {
                $("#result").text(
                "요청 데이터가 올바르지 않습니다.");
            } else {
                var lon, lat;
                var resultCoordinate = resultInfo.coordinate[0];
                if (resultCoordinate.lon.length > 0) {
                    // 구주소
                    lon = resultCoordinate.lon;
                    lat = resultCoordinate.lat;
                } else {
                    // 신주소
                    lon = resultCoordinate.newLon;
                    lat = resultCoordinate.newLat
                }

                var lonEntr, latEntr;

                if (resultCoordinate.lonEntr == undefined && resultCoordinate.newLonEntr == undefined) {
                    lonEntr = 0;
                    latEntr = 0;
                } else {
                    if (resultCoordinate.lonEntr.length > 0) {
                        lonEntr = resultCoordinate.lonEntr;
                        latEntr = resultCoordinate.latEntr;
                    } else {
                        lonEntr = resultCoordinate.newLonEntr;
                        latEntr = resultCoordinate.newLatEntr;
                    }
                }

                if(type === 'departure'){
                    //출발지
                    $('#departure-x').val(lonEntr);
                    $('#departure-y').val(latEntr);
                }else{
                    //도착지
                    $('#destination-x').val(lonEntr);
                    $('#destination-y').val(latEntr);
                }

                alert(`위도y : ${latEntr} / 경도x : ${lonEntr}`);
            }
        },
        error : function(request, status, error) {
            console.log(request);
            console.log("code:"+request.status + "\n message:" + request.responseText +"\n error:" + error);
            // 에러가 발생하면 맵을 초기화함
            // markerStartLayer.clearMarkers();
            // 마커초기화
            map.setCenter(new Tmapv2.LatLng(37.570028, 126.986072));
            $("#result").html("");

        }
    });


});



/**/



var map;
// 페이지가 로딩이 된 후 호출하는 함수입니다.
function initTmap(){
    // map 생성
    // Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmapv3.Map("map_div", {
        center : new Tmapv3.LatLng(37.56520450, 126.98702028),
        width : "90%",	// 지도의 넓이
        height : "500px",	// 지도의 높이
        zoom : 16	// 지도 줌레벨
    });
};

//경로안내 요청 함수
function getRP() {
    var s_latlng = new Tmapv3.LatLng (37.553756, 126.925356);
    var e_latlng = new Tmapv3.LatLng (37.554034, 126.975598);

    var optionObj = {
        reqCoordType:"WGS84GEO", //요청 좌표계 옵셥 설정입니다.
        resCoordType:"WGS84GEO",  //응답 좌표계 옵셥 설정입니다.
        trafficInfo:"Y"
    };

    var params = {
        onComplete:onComplete,
        onProgress:onProgress,
        onError:onError
    };

    // TData 객체 생성
    var tData = new Tmapv3.extension.TData();

    // TData 객체의 경로요청 함수
    tData.getRoutePlanJson(s_latlng, e_latlng, optionObj, params);
}

//경로안내
function onComplete() {
    console.log(this._responseData); //json으로 데이터를 받은 정보들을 콘솔창에서 확인할 수 있습니다.

    var jsonObject = new Tmapv3.extension.GeoJSON();
    var jsonForm = jsonObject.rpTrafficRead(this._responseData);

    //교통정보 표출시 생성되는 LineColor 입니다.
    var trafficColors = {

        // 사용자가 임의로 색상을 설정할 수 있습니다.
        // 교통정보 옵션 - 라인색상
        trafficDefaultColor:"#000000", //교통 정보가 없을 때
        trafficType1Color:"#009900", //원할
        trafficType2Color:"#7A8E0A", //서행
        trafficType3Color:"#8E8111",  //정체
        trafficType4Color:"#FF0000"  //정체
    };
    jsonObject.drawRouteByTraffic(map, jsonForm, trafficColors);
    map.setCenter(new Tmapv3.LatLng(37.55676159947993,126.94734232774672));
    map.setZoom(13);
}

//데이터 로드중 실행하는 함수입니다.
function onProgress(){

}

//데이터 로드 중 에러가 발생시 실행하는 함수입니다.
function onError(){
    alert("onError");
}





