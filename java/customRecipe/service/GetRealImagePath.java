package customRecipe.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

@WebServlet("/getRealImagePath")
public class GetRealImagePath extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String imgName = request.getParameter("imgName");
     String realPath = getServletContext().getRealPath("/upload/" + imgName);
     
     // 절대 경로를 HTTP URL로 변환
     String httpPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/ccm/upload/" + imgName;
     System.out.println(httpPath);
     
     
     Map<String, String> responseData = new HashMap<>();
     responseData.put("realImagePath", httpPath);

     response.setContentType("application/json");
     response.getWriter().write(new Gson().toJson(responseData));
 }
}
