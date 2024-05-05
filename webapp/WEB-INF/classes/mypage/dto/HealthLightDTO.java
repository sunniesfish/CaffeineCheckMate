package mypage.dto;

public class HealthLightDTO {
	private String CAL_DATE;
	private String M_ID;
	private String CAL_COLOR;
	private int CAL_DAILYCF;
	
	public HealthLightDTO(String cAL_DATE, String m_ID, String cAL_COLOR, int cAL_DAILYCF) {
		CAL_DATE = cAL_DATE	;
		M_ID = m_ID;
		CAL_COLOR = cAL_COLOR;
		CAL_DAILYCF = cAL_DAILYCF;
	}
	public String getCAL_DATE() {
		return CAL_DATE;
	}
	public void setCAL_DATE(String cAL_DATE) {
		CAL_DATE = cAL_DATE;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getCAL_COLOR() {
		return CAL_COLOR;
	}
	public void setCAL_COLOR(String cAL_COLOR) {
		CAL_COLOR = cAL_COLOR;
	}
	public int getCAL_DAILYCF() {
		return CAL_DAILYCF;
	}
	public void setCAL_DAILYCF(int cAL_DAILYCF) {
		CAL_DAILYCF = cAL_DAILYCF;
	}
	@Override
	public String toString() {
		return "HealthLightDTO [CAL_DATE=" + CAL_DATE + ", M_ID=" + M_ID + ", CAL_COLOR=" + CAL_COLOR + ", CAL_DAILYCF="
				+ CAL_DAILYCF + "]";
	}
	
	
}