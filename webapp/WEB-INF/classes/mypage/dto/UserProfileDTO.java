package mypage.dto;

public class UserProfileDTO {
	private String M_NICKNAME;
	private int P_NO;
	private String M_ID;
	private int P_WEIGHT;
	private String P_IMG_REAL;
	private String P_IMG_COPY;
	
	public UserProfileDTO() {
		
	}
	
	public UserProfileDTO(String m_NICKNAME, int p_NO, String m_ID, int p_WEIGHT, String p_IMG_REAL, String p_IMG_COPY) {
		M_NICKNAME = m_NICKNAME;
		P_NO = p_NO;
		M_ID = m_ID; 
		P_WEIGHT= p_WEIGHT;
		P_IMG_REAL = p_IMG_REAL;
		P_IMG_COPY= p_IMG_COPY;
	}
	public String getM_NICKNAME() {
		return M_NICKNAME;
	}
	public void setM_NICKNAME(String m_NICKNAME) {
		M_NICKNAME = m_NICKNAME;
	}
	public int getP_NO() {
		return P_NO;
	}
	public void setP_NO(int p_NO) {
		P_NO = p_NO;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public int getP_WEIGHT() {
		return P_WEIGHT;
	}
	public void setP_WEIGHT(int p_WEIGHT) {
		P_WEIGHT = p_WEIGHT;
	}
	public String getP_IMG_REAL() {
		return P_IMG_REAL;
	}

	public void setP_IMG_REAL(String p_IMG_REAL) {
		P_IMG_REAL = p_IMG_REAL;
	}
	public String getP_IMG_COPY() {
		return P_IMG_COPY;
	}
	public void setP_IMG_COPY(String p_IMG_COPY) {
		P_IMG_COPY = p_IMG_COPY;
	}
	public void changeP_WEIGHT(int newP_WEIGHT) {
		this.P_WEIGHT = newP_WEIGHT;
	}
	public void changeP_IMG_COPY(String newP_IMG_COPY) {
		this.P_IMG_COPY = newP_IMG_COPY;
	}

	@Override
	public String toString() {
		return "UserProfileDTO [M_NICKNAME=" + M_NICKNAME + ", P_NO=" + P_NO + ", M_ID=" + M_ID + ", P_WEIGHT="
				+ P_WEIGHT + ", P_IMG_REAL=" + P_IMG_REAL + ", P_IMG_COPY=" + P_IMG_COPY + "]";
	}

	
}