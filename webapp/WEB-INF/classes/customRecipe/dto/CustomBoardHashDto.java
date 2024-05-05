package customRecipe.dto;

public class CustomBoardHashDto {
	private String shot;
	private String milkType;
	private String syrupType;
	private String toppingType;
	private String decaffeinated; 
	private String cus_no;

 

    



	public CustomBoardHashDto() {
	}




	public CustomBoardHashDto(String shot2, String milkType2, String syrupType2, String toppingType2,
			String decaffeinated2) {
		this.shot = shot2;
		this.milkType = milkType2;
		this.syrupType = syrupType2;
		this.toppingType = toppingType2;
		this.decaffeinated = decaffeinated2;
	}




	// getter 및 setter 메서드

	public String getshot() {
		return shot;
	}
	
	public void setshot(String shot) {
		this.shot = shot;
	}
	public String getcus_no() {
		return cus_no;
	}
	
	public void setcus_no(String cus_no) {
		this.cus_no = cus_no;
	}
    public String getmilkType() {
        return milkType;
    }

    public void setmilkType(String milkType) {
        this.milkType = milkType;
    }

    public String getsyrupType() {
        return syrupType;
    }

    public void setsyrupType(String syrupType) {
        this.syrupType = syrupType;
    }

    public String gettoppingType() {
        return toppingType;
    }

    public void settoppingType(String toppingType) {
        this.toppingType = toppingType;
    }

    public String getdecaffeinated() {
        return decaffeinated;
    }

    public void setdecaffeinated(String decaffeinated) {
        this.decaffeinated = decaffeinated;
    }

}

