package coffeeList.dto;

import java.util.ArrayList;
import java.util.HashMap;

import favorite.dto.Favorite;
/* 2열 5행으로 정렬할 예정 (페이지 당 10개의 게시물)
 * 커피리스트 페이지(jsp)에 보여줄 데이터를 가지고 있는 dto? 모르겠음
 * - 
 */
public class CoffeeListPage {
	ArrayList<Coffee> coffeeList;					//게시글 데이터
	HashMap<Integer,Favorite> favorites = null;	//게시글 데이터
	
	private int total;			//총 제품 수
	private int currentPage;	//현재(보고 싶은) 페이지번호
	private int totalPage;		//전체 페이지 수
	private int startPage;		//시작 페이지 번호
	private int endPage;		//마지막 페이지 번호
	
	/*백업 전 - 추후 삭제 예정
	public CoffeeListPage(
			ArrayList<Coffee> coffeeList, 
			HashMap<Integer, Favorite> coffeeFavMap) {
		this.coffeeList = coffeeList;
		this.coffeeFavMap = coffeeFavMap;
	}
	public CoffeeListPage(ArrayList<Coffee> coffeeList) {
		this.coffeeList = coffeeList;
	} */
	
	public CoffeeListPage(ArrayList<Coffee> coffeeList, HashMap<Integer, Favorite> coffeeFavMap, int total, int currentPage, int size) {
		this.coffeeList = coffeeList;
		this.favorites = coffeeFavMap;
		paging(total, currentPage, size);
	}
	
	public CoffeeListPage(ArrayList<Coffee> coffeeList, int total, int currentPage, int size) {
		this.coffeeList = coffeeList;
		this.favorites = new HashMap<Integer,Favorite>(); //비로그인 상태에서는 객체만 생성하여 비워두기만 함
		paging(total, currentPage, size);
	}	
	
	//게시판 뷰 페이징 처리 메서드 
	public void paging(int total, int currentPage, int size) {
		this.total = total;
		this.currentPage = currentPage;
		
		if(total == 0) { //게시글이 없을 경우
			this.totalPage = 0; //총 page의 수
			this.startPage = 0; //페이지 블럭 내 시작페이지 번호
			this.endPage = 0;   //페이지 블럭 내 끝페이지 번호
		}else { 		 //게시글이 존재하는 경우
			//총 page수 = 총 게시글 수 / 1페이지 당 출력할 게시글 수
			this.totalPage = total / size;
			if (total % size > 0) { //나머지가 0보다 클 경우 
				this.totalPage++;	//페이지 1장 더 ex) 게시글 51 > 페이지 6
			}
			
			// 페이지 블럭 설정 (5개씩 조회) 부분
			//modVal = 현재 페이지를 페이지 블럭 사이즈(하단 목차 1,2,3,4,5-5개)로
			//나눈 것의 나머지를 의미
			//ex) 현재 페이지가 7일 경우 나머지 2를 반환
			int modVal = currentPage % 5;
			
			//시작 페이지 설정 부분
			//ex) 현재 페이지 7일 경우 (나눌 시 소수 없이 정수로 계산) 
			//ex) 7 / 5 * 5 + 1 = 5 (5가 시작 페이지로 보여짐)
			this.startPage = (currentPage / 5) * 5 + 1;
			if (modVal == 0) { //나머지가 0일 경우? 5, 10...
				this.startPage = startPage - 5; //현재 페이지가 블럭 마지막 페이지일 경우
												//보여지는 시작페이지에서 5를 뺌
			}
			
			//엔드 페이지 설정
			this.endPage = startPage + 4; //시작 페이지로부터 4개 뒤 페이지가 마지막
										  //ex) 1 -> 4개 뒤 -> 5(마지막 페이지)
			if (this.endPage > this.totalPage) { //마지막 페이지가 총 페이지 수와
				this.endPage = this.totalPage;	 //일치할 때 위의 연산을 하지 않고 
			}									 //페이지블록 끝이 마지막 페이지로 보여짐
		}
	}
	
	//하기 게시글 목록이 존재하지 않을 경우와 존재할 경우를 boolean을 이용하여 return하는 메서드
	//게시글목록이 없다면->총 page수가 0과 같다는 뜻하는 true반환
	public boolean hasNoCoffeeList() {
		return this.totalPage==0;//totalPages는 총 page수가 0과 같다
	}
	//게시글목록이 있다면->총 page수가 0초과라는 것을 뜻하는 true반환
	public boolean hasCoffeeList() {
		return this.totalPage>0;//totalPages는 총 page수가 1 이상이다
	}
	
	//하기 게시물 데이터 목록 게터,세터
	public ArrayList<Coffee> getCoffeeList() {
		return coffeeList;
	}
	public void setCoffeeList(ArrayList<Coffee> coffeeList) {
		this.coffeeList = coffeeList;
	}
	public HashMap<Integer, Favorite> getFavorites() {
		return favorites;
	}
	public void setFavorites(HashMap<Integer, Favorite> coffeeFavMap) {
		this.favorites = coffeeFavMap;
	}
	
	//페이징을 위한 필드는 가져오는 용도로 게터만 설정
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
}
