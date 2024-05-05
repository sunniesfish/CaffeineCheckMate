package global.dto;
import java.util.ArrayList;
import java.util.HashMap;
/*
 * 로그인 페이지에 들어갈 요소 정리
 */

import customRecipe.dto.CustomBoardListDto;
import mypage.dto.UserProfileDTO;


public class Main {
	HashMap<Integer, Object> favorites;
	UserProfileDTO userProfileDTO;
	int calculationResult=0;
	double recommendedIntake =400;
	String color = "#BFFE01";
	ArrayList<CustomBoardListDto> CustomBoardListDao;
//	추천레시피 관련필드
	
	public Main(HashMap favMap, int  calculatedResult, ArrayList<CustomBoardListDto> CustomBoardListDao) { //수정필요
		this.favorites = favMap;
		this.calculationResult = calculatedResult;
		this.CustomBoardListDao = CustomBoardListDao;
	}

	public Main(HashMap favMap, UserProfileDTO profile,int  calculatedResult,ArrayList<CustomBoardListDto> CustomBoardListDao) { //수정필요
		this.favorites = favMap;
		this.userProfileDTO = profile;
		this.calculationResult = calculatedResult;
		this.CustomBoardListDao = CustomBoardListDao;
	}
	public ArrayList<CustomBoardListDto> getcustomBoardListDao() {
		return CustomBoardListDao;
	}
	
	
	public void setCustomBoardListDao(ArrayList<CustomBoardListDto> customBoardListDao) {
		CustomBoardListDao = customBoardListDao;
	}

	
	public HashMap<Integer, Object> getfavorites() {
		return favorites;
	}
	public void setfavorites(HashMap<Integer, Object> favorites) {
		this.favorites = favorites;
	}
	public int getCalculationResult() {
		return calculationResult;
	}
	public void setCalculationResult(int calculationResult) {
		this.calculationResult = calculationResult;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getRecommendedIntake() {
		return recommendedIntake;
	}
	public void setRecommendedIntake(double weight) {
		this.recommendedIntake = weight*6.4;
	}
	public UserProfileDTO getUserProfileDTO() {
		return userProfileDTO;
	}
	public void setUserProfileDTO(UserProfileDTO userProfile) {
		this.userProfileDTO = userProfile;
	}
}
