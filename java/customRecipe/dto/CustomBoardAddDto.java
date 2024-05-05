package customRecipe.dto;

public class CustomBoardAddDto {
	String cus_no;
	String m_id; 
	String c_no;
	String cus_name;
	String cus_content;
	String cus_regdate;
	int cus_sumgood;
	String cus_img;
	String cus_img_realname;
	
	 
	public CustomBoardAddDto(String cus_no, String m_id, String c_no, String cus_name, String cus_content,
			String cus_regdate, int cus_sumgood,String cus_img, String cus_img_realname) {
		this.cus_no = cus_no;
		this.m_id = m_id;
		this.c_no = c_no;
		this.cus_name = cus_name;
		this.cus_content = cus_content;
		this.cus_regdate = cus_regdate;
		this.cus_sumgood = cus_sumgood;
		this.cus_img = cus_img;
		this.cus_img_realname = cus_img_realname;
	}
	
	
	
	
	public CustomBoardAddDto() {
		
	}




	public String getcus_no() {
		return cus_no;
	}
	public void setcus_no(String cus_no) {
		this.cus_no = cus_no;
	}
	public int getcus_sumgood() {
		return cus_sumgood;
	}
	public void setcus_sumgood(int cus_sumgood) {
		this.cus_sumgood = cus_sumgood;
	}
	public String getm_id() {
		return m_id;
	}
	public void setm_id(String m_id) {
		this.m_id = m_id;
	}
	public String getc_no() {
		return c_no;
	}
	public void setc_no(String c_no) {
		this.c_no = c_no;
	}
	public String getcus_name() {
		return cus_name;
	}
	public void setcus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getcus_content() {
		return cus_content;
	}
	public void setcus_content(String cus_content) {
		this.cus_content = cus_content;
	}
	public String getcus_img() {
		return cus_img;
	}
	public void setcus_img(String cus_img) {
		this.cus_img = cus_img;
	}
	public String getcus_img_realname() {
		return cus_img_realname;
	}
	public void setcus_img_realname(String cus_img_realname) {
		this.cus_img_realname = cus_img_realname;
	}


	public String getcus_regdate() {
		return cus_regdate;
	}


	public void setcus_regdate(String cus_regdate) {
		this.cus_regdate = cus_regdate;
	}
	
	
}
