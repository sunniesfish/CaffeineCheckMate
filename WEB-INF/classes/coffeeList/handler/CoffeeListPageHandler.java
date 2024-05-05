package coffeeList.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coffeeList.dto.CoffeeListPage;
import coffeeList.service.CoffeeListPageService;
import controller.CommandHandler;

public class CoffeeListPageHandler implements CommandHandler{
	
	CoffeeListPageService coffeeListService = new CoffeeListPageService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			System.out.println("리스트 뷰 핸들러 들어왔음");
			//반환되는 ArrayList<Coffee> coffeeList를 setAttribute에 저장
			
			//page 파라미터 받음 / default = 1
			int page = 1;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			CoffeeListPage coffeeListPage = null;
			
			// 검색로직 추가
			//searchType:검색 옵션(브랜드,제품명) / searchQuery:클라이언트 입력값
			String searchType = request.getParameter("searchType");
			String searchQuery = request.getParameter("searchQuery");
			
			// 현재 멤버 id 세션
			Object mId =  request.getSession().getAttribute("AUTH_USER_ID");
			String memberId = mId != null? mId.toString() : null;
			boolean isAdmin = false;
			
			if (memberId != null) {
				isAdmin = coffeeListService.checkAdmin(memberId);
			}
			
			//회원
			if (memberId != null) {
				if (searchType != null && 
					searchQuery != null && 
					!searchQuery.isEmpty()) { //검색 시
					coffeeListPage = coffeeListService.searchAuthCoffee(searchType, memberId ,searchQuery, page);
				} else {//비검색 시
					coffeeListPage = coffeeListService.getCoffeeList(memberId, page);
				}
				request.setAttribute("isAdmin", coffeeListService.checkAdmin(memberId)); // 관리자 여부 체크	
			
			//비회원
			} else {	//검색 시
				if (searchType != null &&  
					searchQuery != null &&
					!searchQuery.isEmpty()) {	
					coffeeListPage = coffeeListService.searchCoffee(searchType, searchQuery, page);
				} else {	//비검색 시
					coffeeListPage = coffeeListService.notAuthCoffeeList(page);
				}
			}		
			
			request.setAttribute("CoffeeListPage", coffeeListPage);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("isAdmin", isAdmin);
			
	    	System.out.println("리스트 뷰 핸들러 리턴 전");
			return "/views/screens/coffeeList_index.jsp";
		}catch (Exception e){
			System.out.println("coffeelist handler 에러 메시지: " + e.getMessage());
			return "에러";
		}
	}
}

//커멘드핸들러 임플리먼트
//전체적인 순서 핸들러 > 서비스 호출 > dao dto 처리 리턴