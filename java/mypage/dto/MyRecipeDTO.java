package mypage.dto;

public class MyRecipeDTO {
	private int CUS_NO;
	private String M_ID;
	private String CUS_TITLE;
	private String CUS_IMG_COPY;
	private String CUS_CONTENT;
	private String CUS_REGDATE;
	private String CUS_SUMGOOD;
	
	public MyRecipeDTO(int cUS_NO, String m_ID, String cUS_TITLE, String cUS_IMG_COPY, String cUS_CONTENT, String cUS_REGDATE, String cUS_SUMGOOD) {
		CUS_NO = cUS_NO;
		M_ID = m_ID;
		CUS_TITLE = cUS_TITLE;
		CUS_IMG_COPY = cUS_IMG_COPY;
		CUS_CONTENT = cUS_CONTENT;
		CUS_REGDATE = cUS_REGDATE;
		CUS_SUMGOOD = cUS_SUMGOOD;
	}
	public int getCUS_NO() {
		return CUS_NO;
	}
	public void setCUS_NO(int cUS_NO) {
		CUS_NO = cUS_NO;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getCUS_TITLE() {
		return CUS_TITLE;
	}
	public void setCUS_TITLE(String cUS_TITLE) {
		CUS_TITLE = cUS_TITLE;
	}
	public String getCUS_IMG_COPY() {
		return CUS_IMG_COPY;
	}
	public void setCUS_IMG_COPY(String cUS_IMG_COPY) {
		CUS_IMG_COPY = cUS_IMG_COPY;
	}
	public String getCUS_CONTENT() {
		return CUS_CONTENT;
	}
	public void setCUS_CONTENT(String cUS_CONTENT) {
		CUS_CONTENT = cUS_CONTENT;
	}
	public String getCUS_REGDATE() {
		return CUS_REGDATE;
	}
	public void setCUS_REGDATE(String cUS_REGDATE) {
		CUS_REGDATE = cUS_REGDATE;
	}
	public String getCUS_SUMGOOD() {
		return CUS_SUMGOOD;
	}
	public void setCUS_SUMGOOD(String cUS_SUMGOOD) {
		CUS_SUMGOOD = cUS_SUMGOOD;
	}
	@Override
	public String toString() {
		return "MyRecipeDTO [CUS_NO=" + CUS_NO + ", M_ID=" + M_ID + ", CUS_TITLE=" + CUS_TITLE + ", CUS_IMG_COPY="
				+ CUS_IMG_COPY + ", CUS_CONTENT=" + CUS_CONTENT + ", CUS_REGDATE=" + CUS_REGDATE + ", CUS_SUMGOOD="
				+ CUS_SUMGOOD + "]";
	}
	
	
}