package user;

public class home {
	
	int hYear=0;// 집 건축년도
	String hName=""; // 집 이름
	int hFloor=-2; //층수
	double hArea=0; // 면적
	int hCate=-1; //카테고리(아파트, 오피스텔, 연립주택 )
	String addDong;// 주소-법정동
	String addJibun;// 지번
	int warFee;// 보증금
	int renFee; // 월세
	double pointX; // x좌표
	double pointY; // y좌표)
	
	public void printHome() {
		System.out.println(this.hYear);
		System.out.println(this.hName);
		System.out.println(this.hFloor);
		System.out.println(this.hArea);
		System.out.println(this.hCate);
		System.out.println(this.addDong);
		System.out.println(this.addJibun);
		System.out.println(this.warFee);
		System.out.println(this.renFee);
		System.out.println(this.pointX);
		System.out.println(this.pointY);
		System.out.println("----------------");
	}
}
