
package mypage.handler;

import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.CommandHandler;
import mypage.dto.UserProfileDTO;
import mypage.service.MypageService;

///editMyProfile.do = mypage.handler.EditMyProfileHandler
public class EditMyProfileUpdateHandler extends HttpServlet implements CommandHandler {
	
	private static final long serialVersionUID = 1L;
	
	public static final String FORM_INDEX = "/main.do";
	public static final String FORM_MYPAGE = "/mypage.do";
	//본인의 파일업로드경로
	//프로필폴더 우클릭 > 프로퍼리 > 로케이션복사 C:\Users\USER\git\CaffeineCheckMate\ccm\src\main\webapp\resources\profile
	public static final String FILE_ROOT_PATH = "C:\\icanjava\\tomcat\\webapps\\ROOT\\resources\\profile";
	// 애플리케이션 루트 경로 얻기
	//public static final String APP_ROOT = req.getServletContext().getRealPath("/");
	
	private MypageService mypageService = new MypageService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
		System.out.println("프로필 수정 핸들러 접근");
		
        HttpSession session = req.getSession(false); // 세션이 없으면 null 반환
        
        try {
        	
	        if (session == null || session.getAttribute("AUTH_USER_ID") == null) {
	            return FORM_INDEX; // 비로그인 시 이동할 페이지
	        }
			
	        // 멀티파트 폼 데이터 처리
	        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
	        
	        UserProfileDTO userProfile = new UserProfileDTO();

			String m_id = (String) session.getAttribute("AUTH_USER_ID");
			System.out.println("로그인한 아이디 값 유무 체크 m_id : "+m_id);
			
			userProfile.setM_ID(m_id);

			if (isMultipart) {
		        	
				FileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            
	            //파일사이즈 정의 > 초과시 입셉션발생
	            upload.setFileSizeMax(1024 * 1024 * 10); // 파일 크기 제한 (10MB)
	            
	            java.util.List<FileItem> items = upload.parseRequest(req);
	
	            for (FileItem item : items) {
	            	
	            	//일반 항목일경우 <input type="text">
	                if (item.isFormField()) {
	                	// 폼 데이터 처리
	                    String fieldName = item.getFieldName();
	                    String fieldValue = item.getString("UTF-8");
	                    
	                    switch (fieldName) {
	                        case "m_ID":
	                            userProfile.setM_ID(fieldValue);
	                            break;
	                        case "m_NICKNAME":
	                        	userProfile.setM_NICKNAME(fieldValue);
	                        	break;
	                        case "p_WEIGHT":
	                            Integer weight = Integer.parseInt(fieldValue);
	                            userProfile.setP_WEIGHT(weight);
	                            break;
	                        case "p_IMG_COPY":
	                            userProfile.setP_IMG_COPY(fieldValue);
	                            break;
	                    }
	                    
	                //파일업로드 항목일경우 <input type="file">
	                } else {
	                	//1. 업로드 파일명 가져오기
                        String originalFileName = item.getName();
                        
                        //2. 업로드한 파일이 있을때만 파일업로드 로직 수행(업로드할 파일이 업으면 파일명이 당연히 없다.)
                        if(originalFileName != null && !originalFileName.isEmpty()) {
	                        //2.1 파일 확장자 얻기
	                        String fileExtension = "";
	                        int lastDotIndex = originalFileName.lastIndexOf(".");
	                        if (lastDotIndex >= 0) {
	                            fileExtension = originalFileName.substring(lastDotIndex);
	                        }
	                        
	                        // 2.2 UUID를 사용하여 파일명 생성
	                        String uuid = UUID.randomUUID().toString() + fileExtension;
	                        
		                    //2.3 업로드 경로 셋팅
		                    String filePath = FILE_ROOT_PATH;
		                    //userProfile.setP_FILE_PATH(filePath);
	                        
	                        // 2.4 기존 파일이 있는지 확인 후 삭제
	                        String existingFileName = userProfile.getP_IMG_COPY(); // 현재 저장된 파일명
	                        if (existingFileName != null && !existingFileName.isEmpty()) {
	                            File existingFile = new File(filePath, existingFileName);
	                            if (existingFile.exists()) {
	                                existingFile.delete(); // 기존 파일 삭제
	                            }
	                        }
		                    
	                        // 2.5 신규 파일 업로드 시작
		                    File uploadDir = new File(filePath);
		                    
		                    //  2.6 업로드할 디렉토리가 없으면 생성한다.
		                    if (!uploadDir.exists()) {
		                        uploadDir.mkdirs(); 
		                        System.out.println("Created upload directory.");
		                    }
		
		                    // 2.7 업로드된 파일을 지정된 디렉토리에 저장
		                    File uploadedFile = new File(uploadDir, uuid);
		                    item.write(uploadedFile);
		                    System.out.println("File uploaded to: " + uploadedFile.getAbsolutePath());
		                    
		                    //2.8 update문 수행을위해 dto에 setter
	                        // UserProfileDTO에 UUID 및 원래 파일명 설정
	                        userProfile.setP_IMG_COPY(uuid); // UUID로 저장
	                        userProfile.setP_IMG_REAL(originalFileName); // 진짜 파일명
	                    
                        }
                        
                        PrintWriter out = res.getWriter();
                        //update 서비스 실행
	                    if(mypageService.updateProfile(userProfile)) {
	                    	//성공후 mypage.do로 이동
	                		out.println("<script>alert('프로필수정 완료하였습니다.'); location.href='/mypage.do';</script>");
	                		out.flush();
	                    }else {
	                		out.println("<script>alert('프로필수정 실패하였습니다.'); location.href='/mypage.do';</script>");
	                		out.flush();    	
	                    }
	                    
	                }
	            }
	        }				
	       
		} catch (Exception e) {
			System.out.println("editMyProfileUpdateHandler process에서 오류발생");
			e.printStackTrace();
		} 
			 
		return FORM_INDEX;
		
    }

}
