package calendar.dto;

import java.sql.Date;

public class Calendar {
	Date CAL_DATE;
	String M_ID;
	int CAL_DAILYCF = 0;
	String CAL_COLOR;
	

	public Calendar(Date cAL_DATE, String m_ID, int cAL_DAILYCF, String cAL_COLOR) {
		CAL_DATE = cAL_DATE;
		M_ID = m_ID;
		CAL_DAILYCF = cAL_DAILYCF;
		CAL_COLOR = cAL_COLOR;
	}
	public Date getCAL_DATE() {
		return CAL_DATE;
	}
	public void setCAL_DATE(Date cAL_DATE) {
		CAL_DATE = cAL_DATE;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public int getCAL_DAILYCF() {
		return CAL_DAILYCF;
	}
	public void setCAL_DAILYCF(int cAL_DAILYCF) {
		CAL_DAILYCF = cAL_DAILYCF;
	}	
	public String getCAL_COLOR() {
		return CAL_COLOR;
	}
	public void setCAL_COLOR(String cAL_COLOR) {
		CAL_COLOR = cAL_COLOR;
	}

}
