package controller;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.handler.NullHandler;


@WebServlet(urlPatterns = {"*.do"},
		    initParams = { 
@WebInitParam(name = "configFile", value = "/WEB-INF/config/controller.properties")})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Map<String, CommandHandler> ControleMap = new HashMap<String, CommandHandler>();
    
	public void init() throws ServletException {
	    String configFile = getInitParameter("configFile");
	    Properties prop = new Properties(); 
	    String configFilePath = getServletContext().getRealPath(configFile);

	    try (FileReader fis = new FileReader(configFilePath)) {
	        prop.load(fis); 
	        prop.forEach((key, value) -> {  
	            String command = (String) key;
	            String handlerClassName = (String) value;
	            
	            try { 
	                Class<?> handlerClass = Class.forName(handlerClassName);
	                CommandHandler handlerInstance = 
	                		(CommandHandler) handlerClass.getDeclaredConstructor().newInstance();
	                ControleMap.put(command, handlerInstance);
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
	            		NoSuchMethodException | InvocationTargetException e) {
	                e.printStackTrace();
	            }
	        });
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("doGet");
		
		String requestURI = request.getRequestURI().toString();
		String command = requestURI.substring(request.getContextPath().length());
		String viewPage = null;
		CommandHandler handler = null;
		

		System.out.println("request : "+requestURI);
		System.out.println("command : "+command);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		if(requestURI.indexOf(request.getContextPath())==0) {
			handler = ControleMap.get(command);
			try {
				viewPage = handler.process(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if(viewPage != null) {
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doGet(request, response);
	}
}