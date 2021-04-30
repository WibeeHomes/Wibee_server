package user;


import java.util.ArrayList;

public class CityDTO {
    private static ArrayList<CityCode> cityArr = new ArrayList<CityCode>();
    
    public ArrayList<CityCode> getCityArr() {
    	return cityArr;
    }
    public CityDTO(){
        
        cityArr.add(new CityCode("서울특별시 종로구",11110));
        cityArr.add(new CityCode("서울특별시 중구",11140));
        cityArr.add(new CityCode("서울특별시 용산구",11170));
        cityArr.add(new CityCode("서울특별시 성동구",11200));
        cityArr.add(new CityCode("서울특별시 광진구",11215));
        cityArr.add(new CityCode("서울특별시 동대문구",11230));
        cityArr.add(new CityCode("서울특별시 중랑구",11260));
        cityArr.add(new CityCode("서울특별시 성북구",11290));
        cityArr.add(new CityCode("서울특별시 강북구",11305));
        cityArr.add(new CityCode("서울특별시 도봉구",11320));
        cityArr.add(new CityCode("서울특별시 노원구",11350));
        cityArr.add(new CityCode("서울특별시 은평구",11380));
        cityArr.add(new CityCode("서울특별시 서대문구",11410));
        cityArr.add(new CityCode("서울특별시 마포구",11440));
        cityArr.add(new CityCode("서울특별시 양천구",11470));
        cityArr.add(new CityCode("서울특별시 강서구",11500));
        cityArr.add(new CityCode("서울특별시 구로구",11530));
        cityArr.add(new CityCode("서울특별시 금천구",11545));
        cityArr.add(new CityCode("서울특별시 영등포구",11560));
        cityArr.add(new CityCode("서울특별시 동작구",11590));
        cityArr.add(new CityCode("서울특별시 관악구",11620));
        cityArr.add(new CityCode("서울특별시 서초구",11650));
        cityArr.add(new CityCode("서울특별시 강남구",11680));
        cityArr.add(new CityCode("서울특별시 송파구",11710));
        cityArr.add(new CityCode("서울특별시 강동구",11740));

        cityArr.add(new CityCode("부산광역시 중구",26110));
        cityArr.add(new CityCode("부산광역시 서구",26140));
        cityArr.add(new CityCode("부산광역시 동구",26170));
        cityArr.add(new CityCode("부산광역시 영도구",26200));
        cityArr.add(new CityCode("부산광역시 부산진구",26230));
        cityArr.add(new CityCode("부산광역시 동래구",26260));
        cityArr.add(new CityCode("부산광역시 남구",26290));
        cityArr.add(new CityCode("부산광역시 북구",26320));
        cityArr.add(new CityCode("부산광역시 해운대구",26350));
        cityArr.add(new CityCode("부산광역시 사하구",26380));
        cityArr.add(new CityCode("부산광역시 금정구",26410));
        cityArr.add(new CityCode("부산광역시 강서구",26440));
        cityArr.add(new CityCode("부산광역시 연제구",26470));
        cityArr.add(new CityCode("부산광역시 수영구",26500));
        cityArr.add(new CityCode("부산광역시 사상구",26530));
        cityArr.add(new CityCode("부산광역시 기장군",26710));

        cityArr.add(new CityCode("대구광역시 중구",27110));
        cityArr.add(new CityCode("대구광역시 동구",27140));
        cityArr.add(new CityCode("대구광역시 서구",27170));
        cityArr.add(new CityCode("대구광역시 남구",27200));
        cityArr.add(new CityCode("대구광역시 북구",27230));
        cityArr.add(new CityCode("대구광역시 수성구",27260));
        cityArr.add(new CityCode("대구광역시 달서구",27290));
        cityArr.add(new CityCode("대구광역시 달성군",27710));


        cityArr.add(new CityCode("인천광역시 중구",28110));
        cityArr.add(new CityCode("인천광역시 동구",28140));
        cityArr.add(new CityCode("인천광역시 미추홀구",28177));
        cityArr.add(new CityCode("인천광역시 연수구",28185));
        cityArr.add(new CityCode("인천광역시 남동구",28200));
        cityArr.add(new CityCode("인천광역시 부평구",28237));
        cityArr.add(new CityCode("인천광역시 계양구",28245));
        cityArr.add(new CityCode("인천광역시 서구",28260));
        cityArr.add(new CityCode("인천광역시 강화군",28710));
        cityArr.add(new CityCode("인천광역시 옹진군",28720));
       
        cityArr.add(new CityCode("광주광역시 동구",29110));
        cityArr.add(new CityCode("광주광역시 서구",29140));
        cityArr.add(new CityCode("광주광역시 남구",29155));
        cityArr.add(new CityCode("광주광역시 북구",29170));
        cityArr.add(new CityCode("광주광역시 광산구",29200));

        cityArr.add(new CityCode("대전광역시 동구",30110));
        cityArr.add(new CityCode("대전광역시 중구",30140));
        cityArr.add(new CityCode("대전광역시 서구",30170));
        cityArr.add(new CityCode("대전광역시 유성구",30200));
        cityArr.add(new CityCode("대전광역시 대덕구",30230));

        cityArr.add(new CityCode("울산광역시 중구",31110));
        cityArr.add(new CityCode("울산광역시 남구",31140));
        cityArr.add(new CityCode("울산광역시 동구",31170));
        cityArr.add(new CityCode("울산광역시 북구",31200));
        cityArr.add(new CityCode("울산광역시 울주군",31710));

        cityArr.add(new CityCode("세종특별자치시",36110));

        cityArr.add(new CityCode("경기도 수원시 정자동",41111));
        cityArr.add(new CityCode("경기도 수원시 권선구",41113));
        cityArr.add(new CityCode("경기도 수원시 영통구",41117));
        cityArr.add(new CityCode("경기도 수원시 팔달구",41115));
        cityArr.add(new CityCode("경기도 성남시 수정구",41131));
        cityArr.add(new CityCode("경기도 성남시 중원구",41133));
        cityArr.add(new CityCode("경기도 성남시 분당구",41135));  
        cityArr.add(new CityCode("경기도 고양시 일산동구",41285));
        cityArr.add(new CityCode("경기도 고양시 일산서구",41287));
        cityArr.add(new CityCode("경기도 고양시 덕양구",41281));
        cityArr.add(new CityCode("경기도 용인시 처인구",41461));
        cityArr.add(new CityCode("경기도 용인시 기흥구",41463));
        cityArr.add(new CityCode("경기도 용인시 수지구",41465));
        cityArr.add(new CityCode("경기도 부천시",41190));
        cityArr.add(new CityCode("경기도 안산시 단원구",41273));
        cityArr.add(new CityCode("경기도 안산시 상록구",41271));
        cityArr.add(new CityCode("경기도 안양시 동안구",41173));
        cityArr.add(new CityCode("경기도 안양시 만안구",41171));
        cityArr.add(new CityCode("경기도 남양주시",41360));
        cityArr.add(new CityCode("경기도 화성시",41590));
        cityArr.add(new CityCode("경기도 평택시",41220));
        cityArr.add(new CityCode("경기도 의정부시",41150));
        cityArr.add(new CityCode("경기도 시흥시",41390));
        cityArr.add(new CityCode("경기도 파주시",41480));
        cityArr.add(new CityCode("경기도 광명시",41210));
        cityArr.add(new CityCode("경기도 김포시",41570));
        cityArr.add(new CityCode("경기도 군포시",41410));
        cityArr.add(new CityCode("경기도 광주시",41610));
        cityArr.add(new CityCode("경기도 이천시",41500));
        cityArr.add(new CityCode("경기도 양주시",41630));
        cityArr.add(new CityCode("경기도 오산시",41370));
        cityArr.add(new CityCode("경기도 구리시",41310));
        cityArr.add(new CityCode("경기도 안성시",41550));
        cityArr.add(new CityCode("경기도 포천시",41650));
        cityArr.add(new CityCode("경기도 의왕시",41430));
        cityArr.add(new CityCode("경기도 하남시",41450));
        cityArr.add(new CityCode("경기도 여주시",41670));
        cityArr.add(new CityCode("경기도 양평군",41830));
        cityArr.add(new CityCode("경기도 동두천시",41250));
        cityArr.add(new CityCode("경기도 과천시",41290));
        cityArr.add(new CityCode("경기도 가평군",41820));
        cityArr.add(new CityCode("경기도 연천군",41800));

        cityArr.add(new CityCode("강원도 춘천시",42110));
        cityArr.add(new CityCode("강원도 원주시",42130));
        cityArr.add(new CityCode("강원도 강릉시",42150));
        cityArr.add(new CityCode("강원도 동해시",42170));
        cityArr.add(new CityCode("강원도 태백시",42190));
        cityArr.add(new CityCode("강원도 속초시",42210));
        cityArr.add(new CityCode("강원도 삼척시",42230));
        cityArr.add(new CityCode("강원도 홍천군",42720));
        cityArr.add(new CityCode("강원도 횡성군",42730));
        cityArr.add(new CityCode("강원도 영월군",42750));
        cityArr.add(new CityCode("강원도 평창군",42760));
        cityArr.add(new CityCode("강원도 정선군",42770));
        cityArr.add(new CityCode("강원도 철원군",42780));
        cityArr.add(new CityCode("강원도 화천군",42790));
        cityArr.add(new CityCode("강원도 양구군",42800));
        cityArr.add(new CityCode("강원도 인제군",42810));
        cityArr.add(new CityCode("강원도 고성군",42820));
        cityArr.add(new CityCode("강원도 양양군",42830));

        cityArr.add(new CityCode("충청북도 청주시 상당구",43111));
        cityArr.add(new CityCode("충청북도 청주시 서원구",43112));
        cityArr.add(new CityCode("충청북도 청주시 청원구",43114));
        cityArr.add(new CityCode("충청북도 청주시 흥덕구",43113));
        cityArr.add(new CityCode("충청북도 충주시",43130));
        cityArr.add(new CityCode("충청북도 제천시",43150));
        cityArr.add(new CityCode("충청북도 보은군",43720));
        cityArr.add(new CityCode("충청북도 옥천군",43730));
        cityArr.add(new CityCode("충청북도 영동군",43740));
        cityArr.add(new CityCode("충청북도 진천군",43750));
        cityArr.add(new CityCode("충청북도 괴산군",43760));
        cityArr.add(new CityCode("충청북도 음성군",43770));
        cityArr.add(new CityCode("충청북도 단양군",43800));
        cityArr.add(new CityCode("충청북도 증평군",43745));


        cityArr.add(new CityCode("충청남도 천안시 동남구",44131));
        cityArr.add(new CityCode("충청남도 천안시 서북구",44133));
        cityArr.add(new CityCode("충청남도 공주시",44150));
        cityArr.add(new CityCode("충청남도 보령시",44180));
        cityArr.add(new CityCode("충청남도 아산시",44200));
        cityArr.add(new CityCode("충청남도 서산시",44210));
        cityArr.add(new CityCode("충청남도 논산시",44230));
        cityArr.add(new CityCode("충청남도 계룡시",44250));
        cityArr.add(new CityCode("충청남도 당진시",44270));
        cityArr.add(new CityCode("충청남도 금산군",44710));
        cityArr.add(new CityCode("충청남도 부여군",44760));
        cityArr.add(new CityCode("충청남도 서천군",44770));
        cityArr.add(new CityCode("충청남도 청양군",44790));
        cityArr.add(new CityCode("충청남도 홍성군",44800));
        cityArr.add(new CityCode("충청남도 예산군",44810));
        cityArr.add(new CityCode("충청남도 태안군",44825));

        cityArr.add(new CityCode("전라북도 전주시 덕진구",45113));
        cityArr.add(new CityCode("전라북도 전주시 완산구",45111));
        cityArr.add(new CityCode("전라북도 군산시",45130));
        cityArr.add(new CityCode("전라북도 익산시",45140));
        cityArr.add(new CityCode("전라북도 정읍시",45180));
        cityArr.add(new CityCode("전라북도 남원시",45190));
        cityArr.add(new CityCode("전라북도 김제시",45210));
        cityArr.add(new CityCode("전라북도 완주군",45710));
        cityArr.add(new CityCode("전라북도 진안군",45720));
        cityArr.add(new CityCode("전라북도 무주군",45730));
        cityArr.add(new CityCode("전라북도 장수군",45740));
        cityArr.add(new CityCode("전라북도 임실군",45750));
        cityArr.add(new CityCode("전라북도 순창군",45770));
        cityArr.add(new CityCode("전라북도 고창군",45790));
        cityArr.add(new CityCode("전라북도 부안군",45800));

        cityArr.add(new CityCode("전라남도 목포시",46110));
        cityArr.add(new CityCode("전라남도 여수시",46130));
        cityArr.add(new CityCode("전라남도 순천시",46150));
        cityArr.add(new CityCode("전라남도 나주시",46170));
        cityArr.add(new CityCode("전라남도 광양시",46230));
        cityArr.add(new CityCode("전라남도 딤양군",46710));
        cityArr.add(new CityCode("전라남도 곡성군",46720));
        cityArr.add(new CityCode("전라남도 구례군",46730));
        cityArr.add(new CityCode("전라남도 고흥군",46770));
        cityArr.add(new CityCode("전라남도 보성군",46780));
        cityArr.add(new CityCode("전라남도 화순군",46790));
        cityArr.add(new CityCode("전라남도 장흥군",46800));
        cityArr.add(new CityCode("전라남도 강진군",46810));
        cityArr.add(new CityCode("전라남도 해남군",46820));
        cityArr.add(new CityCode("전라남도 영암군",46830));
        cityArr.add(new CityCode("전라남도 무안군",46840));
        cityArr.add(new CityCode("전라남도 함평군",46860));
        cityArr.add(new CityCode("전라남도 영광군",46870));
        cityArr.add(new CityCode("전라남도 장성군",46880));
        cityArr.add(new CityCode("전라남도 완도군",46890));
        cityArr.add(new CityCode("전라남도 진도군",46900));
        cityArr.add(new CityCode("전라남도 신안군",46910));

        cityArr.add(new CityCode("경상북도 포항시 남구",47111));
        cityArr.add(new CityCode("경상북도 포항시 북구",47113));
        cityArr.add(new CityCode("경상북도 경주시",47130));
        cityArr.add(new CityCode("경상북도 김천시",47150));
        cityArr.add(new CityCode("경상북도 안동시",47170));
        cityArr.add(new CityCode("경상북도 구미시",47190));
        cityArr.add(new CityCode("경상북도 영주시",47210));
        cityArr.add(new CityCode("경상북도 영천시",47230));
        cityArr.add(new CityCode("경상북도 상주시",47250));
        cityArr.add(new CityCode("경상북도 문경시",47280));
        cityArr.add(new CityCode("경상북도 경산시",47290));
        cityArr.add(new CityCode("경상북도 군위군",47720));
        cityArr.add(new CityCode("경상북도 의성군",47730));
        cityArr.add(new CityCode("경상북도 청송군",47750));
        cityArr.add(new CityCode("경상북도 영양군",47760));
        cityArr.add(new CityCode("경상북도 영덕군",47770));
        cityArr.add(new CityCode("경상북도 청도군",47820));
        cityArr.add(new CityCode("경상북도 고령군",47830));
        cityArr.add(new CityCode("경상북도 성주군",47840));
        cityArr.add(new CityCode("경상북도 칠곡군",47850));
        cityArr.add(new CityCode("경상북도 예천군",47900));
        cityArr.add(new CityCode("경상북도 봉화군",47920));
        cityArr.add(new CityCode("경상북도 울진군",47930));
        cityArr.add(new CityCode("경상북도 울릉군",47940));

        cityArr.add(new CityCode("경상남도 창원시 의창구",48121));
        cityArr.add(new CityCode("경상남도 창원시 마산합포구",48125));
        cityArr.add(new CityCode("경상남도 창원시 마산회원구",48127));
        cityArr.add(new CityCode("경상남도 창원시 성산구",48123));
        cityArr.add(new CityCode("경상남도 창원시 진해구",48129));
        cityArr.add(new CityCode("경상남도 진주시",48170));
        cityArr.add(new CityCode("경상남도 통영시",48220));
        cityArr.add(new CityCode("경상남도 사천시",48240));
        cityArr.add(new CityCode("경상남도 김해시",48250));
        cityArr.add(new CityCode("경상남도 밀양시",48270));
        cityArr.add(new CityCode("경상남도 거제시",48310));
        cityArr.add(new CityCode("경상남도 양산시",48330));
        cityArr.add(new CityCode("경상남도 의령군",48720));
        cityArr.add(new CityCode("경상남도 함안군",48730));
        cityArr.add(new CityCode("경상남도 창녕군",48740));
        cityArr.add(new CityCode("경상남도 고성군",48820));
        cityArr.add(new CityCode("경상남도 남해군",48840));
        cityArr.add(new CityCode("경상남도 하동군",48850));
        cityArr.add(new CityCode("경상남도 산청군",48860));
        cityArr.add(new CityCode("경상남도 함양군",48870));
        cityArr.add(new CityCode("경상남도 거창군",48880));
        cityArr.add(new CityCode("경상남도 합천군",48890));
        
        cityArr.add(new CityCode("제주특별자치도 제주시",50110));
        cityArr.add(new CityCode("제주특별자치도 서귀포시",50130));
    }
}
