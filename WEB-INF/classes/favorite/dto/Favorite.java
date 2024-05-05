package favorite.dto;

import java.sql.Date;

public class Favorite {

	private String M_ID;
	private int C_NO;
	private String C_NAME;
	private Date C_FAV_DATE;
	private String C_IMAGE="";
	
	public Favorite(String M_ID, int C_NO, String C_NAME, Date C_FAV_DATE, String C_IMAGE) {
		this.M_ID = M_ID;
		this.C_NO = C_NO;
		this.C_NAME = C_NAME;
		this.C_FAV_DATE = C_FAV_DATE;
		this.C_IMAGE = C_IMAGE;
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

	public Date getC_FAV_DATE() {
		return C_FAV_DATE;
	}

	public void setC_FAV_DATE(Date c_FAV_DATE) {
		C_FAV_DATE = c_FAV_DATE;
	}

	public String getC_IMAGE() {
		return C_IMAGE;
	}

	public void setC_IMAGE(String c_IMAGE) {
		C_IMAGE = c_IMAGE;
	}



}
