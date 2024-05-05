package mypage.dto;

public class FavoriteListDTO {
	private String M_ID; //회원ID
	private int C_NO; //제품넘버
	private String C_NAME; //제품명
	private int C_CAFFEINE; //카페인함량
	private String C_IMG_COPY; //제품 이미지 사본 이름
	
	
	public FavoriteListDTO(String m_ID, int c_NO, String c_NAME, int c_CAFFEINE, String c_IMG_COPY) {
		M_ID = m_ID; //회원ID
		C_NO = c_NO; //제품넘버
		C_NAME = c_NAME; //제품명
		C_CAFFEINE = c_CAFFEINE; //카페인함량
		C_IMG_COPY = c_IMG_COPY; // 제품 이미지 사본 이름
	}


	public String getM_ID() {
		return M_ID;
	}


	public void setM_ID(String m_ID) {
		M_ID = m_ID;
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


	public int getC_CAFFEINE() {
		return C_CAFFEINE;
	}


	public void setC_CAFFEINE(int c_CAFFEINE) {
		C_CAFFEINE = c_CAFFEINE;
	}


	public String getC_IMG_COPY() {
		return C_IMG_COPY;
	}


	public void setC_IMG_COPY(String c_IMG_COPY) {
		C_IMG_COPY = c_IMG_COPY;
	}


	@Override
	public String toString() {
		return "FavoriteListDTO [M_ID=" + M_ID + ", C_NO=" + C_NO + ", C_NAME=" + C_NAME + ", C_CAFFEINE=" + C_CAFFEINE
				+ ", C_IMG_COPY=" + C_IMG_COPY + "]";
	}
	
	
	
}