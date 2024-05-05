package coffeeList.dto;

public class Coffee {
	//필드
	int C_NO;			//커피 넘버 pk
	String C_NAME;		//제품 이름
	String C_BRAND;		//브랜드
	int C_CAFFEINE;		//카페인
	int C_SACCHARIDE;	//당도
	int C_CALORIE;		//칼로리
	String C_CONTENT;	//내용
	String C_TYPE;		//타입
	String C_STAGE;		//단계
	String C_IMG_REAL;	//이미지 파일 (실제 이름)
	String C_IMG_COPY;	//이미지 생성 후 부여됨 / 실제 불러오는 이미지 파일 이름
	int C_FAVORITE;		//총 즐겨찾기 수
	String C_IMAGE;		//C_IMG_COPY와 동일 / 팀원과 분할하여 구현 진행
	
	
	//list 조회
	public Coffee(int C_NO,String C_NAME,String C_BRAND,int C_CAFFEINE,String C_IMG_COPY) {
		this.C_NO = C_NO;
		this.C_NAME = C_NAME;
		this.C_BRAND = C_BRAND;
		this.C_CAFFEINE = C_CAFFEINE;
		this.C_IMG_COPY = C_IMG_COPY;
	}
	//list Detail 조회
	public Coffee
		(int C_NO,String C_NAME,String C_BRAND,int C_CAFFEINE, int C_SACCHARIDE,int C_CALORIE,String C_CONTENT,String C_TYPE, String C_STAGE, String C_IMG_REAL, String C_IMG_COPY) {
		this.C_NO = C_NO;
		this.C_NAME = C_NAME;
		this.C_BRAND = C_BRAND;
		this.C_CAFFEINE = C_CAFFEINE;
		this.C_SACCHARIDE = C_SACCHARIDE;
		this.C_CALORIE = C_CALORIE;
		this.C_CONTENT = C_CONTENT;
		this.C_TYPE = C_TYPE;
		this.C_STAGE = C_STAGE;
		this.C_IMG_REAL = C_IMG_REAL;
		this.C_IMG_COPY = C_IMG_COPY;
	}
	//list Add 추가
	public Coffee
		(String C_NAME,String C_BRAND,int C_CAFFEINE,int C_SACCHARIDE,int C_CALORIE,String C_CONTENT, String C_TYPE, String C_STAGE, String C_IMG_REAL, String C_IMG_COPY) {
		this.C_NAME = C_NAME;
		this.C_BRAND = C_BRAND;
		this.C_CAFFEINE = C_CAFFEINE;
		this.C_SACCHARIDE = C_SACCHARIDE;
		this.C_CALORIE = C_CALORIE;
		this.C_CONTENT = C_CONTENT;
		this.C_TYPE = C_TYPE;
		this.C_STAGE = C_STAGE;
		this.C_IMG_REAL = C_IMG_REAL;
		this.C_IMG_COPY = C_IMG_COPY;
	}
	//list Update 수정
	public Coffee
		(String C_NAME,String C_BRAND,int C_CAFFEINE,int C_SACCHARIDE,int C_CALORIE,String C_CONTENT, String C_TYPE, String C_STAGE, String C_IMG_REAL, String C_IMG_COPY, int C_NO) {
		this.C_NO = C_NO; //수정 where절 입력값
		this.C_NAME = C_NAME;
		this.C_BRAND = C_BRAND;
		this.C_CAFFEINE = C_CAFFEINE;
		this.C_SACCHARIDE = C_SACCHARIDE;
		this.C_CALORIE = C_CALORIE;
		this.C_CONTENT = C_CONTENT;
		this.C_TYPE = C_TYPE;
		this.C_STAGE = C_STAGE;
		this.C_IMG_REAL = C_IMG_REAL;
		this.C_IMG_COPY = C_IMG_COPY;
	}
	//사진파일 삭제
	public Coffee(int C_NO,String C_IMG_REAL) {
		this.C_NO = C_NO;
		this.C_IMG_REAL = C_IMG_REAL;
	}
	
	//카페인, 이미지 조회 (계산기)
  public Coffee(int C_NO, int C_CAFFEINE, String C_IMAGE) {
		this.C_NO=C_NO;
		this.C_CAFFEINE=C_CAFFEINE;
		this.C_IMAGE=C_IMAGE;
	}
  //카페인, 이미지 조회 (계산기)
  public Coffee(int C_NO,String C_NAME,  int C_CAFFEINE, String C_IMAGE) {
	  this.C_NO=C_NO;
	  this.C_NAME=C_NAME;
	  this.C_CAFFEINE=C_CAFFEINE;
	  this.C_IMAGE=C_IMAGE;
  }
	
	
	//하기 게터세터 메서드----------------------------------------------------
	public String getC_IMAGE() {
		return C_IMAGE;
	}

	public void setC_IMAGE(String c_IMAGE) {
		C_IMAGE = c_IMAGE;
	}

	public int getC_NO() {
		return C_NO;
	}
	public void setC_NO(int c_NO) {
		C_NO = c_NO;
	}
	public String getC_NAME() {
		return C_NAME;
	}
	public void setC_NAME(String c_NAME) {
		C_NAME = c_NAME;
	}
	public String getC_BRAND() {
		return C_BRAND;
	}
	public void setC_BRAND(String c_BRAND) {
		C_BRAND = c_BRAND;
	}
	public int getC_SACCHARIDE() {
		return C_SACCHARIDE;
	}
	public void setC_SACCHARIDE(int c_SACCHARIDE) {
		C_SACCHARIDE = c_SACCHARIDE;
	}
	public int getC_CALORIE() {
		return C_CALORIE;
	}
	public void setC_CALORIE(int c_CALORIE) {
		C_CALORIE = c_CALORIE;
	}
	public String getC_CONTENT() {
		return C_CONTENT;
	}
	public void setC_CONTENT(String c_CONTENT) {
		C_CONTENT = c_CONTENT;
	}
	public String getC_TYPE() {
		return C_TYPE;
	}
	public void setC_TYPE(String c_TYPE) {
		C_TYPE = c_TYPE;
	}
	public String getC_STAGE() {
		return C_STAGE;
	}
	public void setC_STAGE(String c_STAGE) {
		C_STAGE = c_STAGE;
	}
	public int getC_FAVORITE() {
		return C_FAVORITE;
	}
	public void setC_FAVORITE(int c_FAVORITE) {
		C_FAVORITE = c_FAVORITE;
	}
	public String getC_IMG_REAL() {
		return C_IMG_REAL;
	}
	public void setC_IMG_REAL(String c_IMG_REAL) {
		C_IMG_REAL = c_IMG_REAL;
	}
	public String getC_IMG_COPY() {
		return C_IMG_COPY;
	}
	public void setC_IMG_COPY(String c_IMG_COPY) {
		C_IMG_COPY = c_IMG_COPY;
	}
	
	public int getC_CAFFEINE() {
		return C_CAFFEINE;
	}
	public void setC_CAFFEINE(int c_CAFFEINE) {
		C_CAFFEINE = c_CAFFEINE;
	}

	@Override
	public String toString() {
		return "Coffee [C_NO=" + C_NO + ", C_NAME=" + C_NAME + ", C_BRAND=" + C_BRAND + ", C_CAFFEINE=" + C_CAFFEINE
				+ ", C_SACCHARIDE=" + C_SACCHARIDE + ", C_CALORIE=" + C_CALORIE + ", C_CONTENT=" + C_CONTENT
				+ ", C_TYPE=" + C_TYPE + ", C_STAGE=" + C_STAGE + ", C_IMG_REAL=" + C_IMG_REAL + ", C_IMG_COPY="
				+ C_IMG_COPY + ", C_FAVORITE=" + C_FAVORITE + ", C_IMAGE=" + C_IMAGE + "]";
	}
	
}	