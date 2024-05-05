package coffeeList.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeeList.dto.Coffee;
import coffeeList.service.CoffeeDetailService;
import controller.CommandHandler;

public class CoffeeDetailHandler implements CommandHandler{
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try {
            int coffeeNo = Integer.parseInt(request.getParameter("coffeeNo"));
            
            //System.out.println("int로 변환까지 잘 됨");
            CoffeeDetailService coffeeDetailService = new CoffeeDetailService();
            Coffee coffee = coffeeDetailService.getCoffeeDetail(coffeeNo);
            
            //request.setAttribute("pageNo",pageNo);
            request.setAttribute("coffeeDetail", coffee);
            return "/views/screens/coffeeList_Detail.jsp";
        } catch (Exception e) {
            System.out.println("coffeedetail handler 에러 메시지: " + e.getMessage());
            return "에러";
        }
    }
}