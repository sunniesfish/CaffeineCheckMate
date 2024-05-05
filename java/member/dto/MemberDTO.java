package member.dto;

import java.sql.Timestamp;

public class MemberDTO {
	
	private String dtoPRO;
	private String dtoROLE;
	private String dtoCANCEL;
	private String dtoID;
	private String dtoPW;
	private String dtoNAME;
	private String dtoSSN;
	private String dtoEMAIL;
	private String dtoNICKNAME;
	private String dtoTEL;
	private String dtoGENDER;
	private String dtoSNS;
	private Timestamp  dtoDELETEDATE;
	
	
	public MemberDTO() {
    }
	
	public MemberDTO(String dtoID, String dtoROLE) {
		this.dtoID = dtoID;
		this.dtoROLE = dtoROLE;
	}
	
	public String getDtoID() {
		return dtoID;
	}
	public void setDtoID(String dtoID) {
		this.dtoID = dtoID;
	}
	public String getDtoPW() {
		return dtoPW;
	}
	public void setDtoPW(String dtoPW) {
		this.dtoPW = dtoPW;
	}
	public String getDtoNAME() {
		return dtoNAME;
	}
	public void setDtoNAME(String dtoNAME) {
		this.dtoNAME = dtoNAME;
	}
	public String getDtoSSN() {
		return dtoSSN;
	}
	public void setDtoSSN(String dtoSSN) {
		this.dtoSSN = dtoSSN;
	}
	public String getDtoEMAIL() {
		return dtoEMAIL;
	}
	public void setDtoEMAIL(String dtoEMAIL) {
		this.dtoEMAIL = dtoEMAIL;
	}
	public String getDtoNICKNAME() {
		return dtoNICKNAME;
	}
	public void setDtoNICKNAME(String dtoNICKNAME) {
		this.dtoNICKNAME = dtoNICKNAME;
	}
	public String getDtoTEL() {
		return dtoTEL;
	}
	public void setDtoTEL(String dtoTEL) {
		this.dtoTEL = dtoTEL;
	}
	public String getDtoGENDER() {
		return dtoGENDER;
	}
	public void setDtoGENDER(String dtoGENDER) {
		this.dtoGENDER = dtoGENDER;
	}
	public String getDtoSNS() {
		return dtoSNS;
	}
	public void setDtoSNS(String dtoSNS) {
		this.dtoSNS = dtoSNS;
	}
	public String getDtoPRO() {
		return dtoPRO;
	}
	public void setDtoPRO(String dtoPRO) {
		this.dtoPRO = dtoPRO;
	}
	public String getDtoROLE() {
		return dtoROLE;
	}
	public void setDtoROLE(String dtoROLE) {
		this.dtoROLE = dtoROLE;
	}
	public String getDtoCANCEL() {
		return dtoCANCEL;
	}
	public void setDtoCANCEL(String dtoCANCEL) {
		this.dtoCANCEL = dtoCANCEL;
	}
	public Timestamp getDtoDELETEDATE() {
		return dtoDELETEDATE;
	}

	public void setDtoDELETEDATE(Timestamp dtoDELETEDATE) {
		this.dtoDELETEDATE = dtoDELETEDATE;
	}
	

	
	
}
