package customRecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import customRecipe.dto.CustomBoardListDto;
import jdbc.JdbcUtil;

 

public class CustomBoardListDao {

	
	
	//이미지포함 게시판리스트 출력
	public  ArrayList<CustomBoardListDto> getallList(Connection con) throws SQLException {
		//String sql="select CUS_NO,M_NO,C_NO,CUS_NAME,CUS_CONTENT,CUS_REGDATE,CUS_SUMGOOD from custom order by cus_regdate desc";
		//위 sql은 cus_no조회하는 쿼리문
		String sql = "select num,cus_no,m_id,c_no,CUS_TITLE,CUS_CONTENT,CUS_REGDATE,CUS_SUMGOOD from custom_view";
			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery(sql);
			ArrayList<CustomBoardListDto> list = new ArrayList<>();
	
			while(rs.next()){

//				int CUS_NO = rs.getInt(1);
				CustomBoardListDto dto = new CustomBoardListDto();
				
	            dto.setCUS_NUM(rs.getInt(1));
	            dto.setCUS_NO(rs.getInt(2)); 
	            dto.setm_id(boardnick(con, rs.getString(3)));
	            dto.setc_no(rs.getString(4));
	            dto.setcus_title(rs.getString(5));
	            dto.setCUS_CONTENT(rs.getString(6));
	            
	            dto.setCUS_REGDATE(rs.getString(7));
	            dto.setCUS_SUMGOOD(rs.getString(8));
	            
	            getimg(dto,con); 
	            list.add(dto);
			}
			JdbcUtil.close(rs); JdbcUtil.close(st1);
			return list;
	} 	
	
	
	//메인 리스트
	public  ArrayList<CustomBoardListDto> getmainList(Connection con) throws SQLException {
		String sql = "select num,cus_no,m_id,c_no,CUS_TITLE,CUS_CONTENT,CUS_REGDATE,CUS_SUMGOOD from custom_view where num=?";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = null;
		ArrayList<CustomBoardListDto> list = new ArrayList<>();

		
		
		int allcount = allCount(con);  //33개
		
		int[] arr = new int[9];
		for(int i=0; i<arr.length; i++) {
			int a = (int)(Math.random()*allcount)+1;
			arr[i] = a;
			for(int j=0;j<i; j++) {
				if(arr[j]==arr[i])
				i--;
			}
		
		
		}
			for(int i=0;i<arr.length;i++) {
			pstm.setInt(1, arr[i]);
			rs = pstm.executeQuery();
			
			while(rs.next()){
//				int CUS_NO = rs.getInt(1);
				CustomBoardListDto dto = new CustomBoardListDto();
				
				dto.setCUS_NUM(rs.getInt(1));
				dto.setCUS_NO(rs.getInt(2)); 
				dto.setm_id(boardnick(con, rs.getString(3)));
				 
				dto.setc_no(rs.getString(4));
				dto.setcus_title(rs.getString(5));
				dto.setCUS_CONTENT(rs.getString(6));
				
				dto.setCUS_REGDATE(rs.getString(7));
				dto.setCUS_SUMGOOD(rs.getString(8));
				
				getimg(dto,con); 
				list.add(dto);
			}
			}
			
		JdbcUtil.close(rs); JdbcUtil.close(pstm);
		return list;
		
		
		
	} 	

	
	
	//게시글번호로 해당이미지파일값 불러오기
	public void getimg(CustomBoardListDto dto, Connection con) throws SQLException {
		int num =dto.getcus_no();
	    String sql = "select CUS_IMG_NO,CUS_NO,CUS_IMG_REAL,CUS_IMG_COPY from custom_img where cus_no=?";
	    	PreparedStatement pstm = con.prepareStatement(sql);
	        pstm.setInt(1,num);
	        ResultSet rs = pstm.executeQuery();
	        if (rs.next()) {
	            dto.setCUS_IMG_NO(rs.getInt(1));
	            dto.setcus_img_realname(rs.getString(4));
	}
	        JdbcUtil.close(pstm); JdbcUtil.close(rs);
	        
}
	public ArrayList<CustomBoardListDto> readlist(int count,int allcount,Connection con) throws SQLException {
		
		String sql = "select num,cus_no,m_id,c_no,CUS_TITLE,CUS_CONTENT,CUS_REGDATE,CUS_SUMGOOD from custom_view where num between ? and ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, allcount-7);
		pstm.setInt(2, allcount);
		ResultSet rs = pstm.executeQuery();
		ArrayList<CustomBoardListDto> list = new ArrayList<>();

		while(rs.next()){

//			int CUS_NO = rs.getInt(1);
			CustomBoardListDto dto = new CustomBoardListDto();
			
			dto.setCUS_NUM(rs.getInt(1));
            dto.setCUS_NO(rs.getInt(2)); 
            
        	dto.setm_id(boardnick(con, rs.getString(3)));
            
            dto.setc_no(rs.getString(4));
            dto.setcus_title(rs.getString(5));
            dto.setCUS_CONTENT(rs.getString(6));
            dto.setCUS_REGDATE(rs.getString(7));
            dto.setCUS_SUMGOOD(rs.getString(8));
            
            getimg(dto,con); 
            list.add(dto);
		}
		JdbcUtil.close(rs); JdbcUtil.close(pstm);
		return list;
	
	}
	
public ArrayList<CustomBoardListDto> viewhash(CustomBoardListDto dto,Connection con) throws SQLException {
		
		String hsql = "select CUS_HASH_SHOT,CUS_HASH_MILK,CUS_HASH_SYRUP,CUS_HASH_TOP,CUS_HASH_DECAF from custom_hash where cus_no=?";
		PreparedStatement pstm1 = con.prepareStatement(hsql);
		pstm1.setInt(1,dto.getcus_no());
		ResultSet rs1 = pstm1.executeQuery();
		
		ArrayList<CustomBoardListDto> list2 = new ArrayList<>();
		
		while(rs1.next()) {
		
		dto.setshot(rs1.getString(1));
		dto.setmilkType(rs1.getString(2));
		dto.setsyrupType(rs1.getString(3));
		dto.settoppingType(rs1.getString(4));
		dto.setdecaffeinated(rs1.getString(5));
		
			list2.add(dto);
		}
		JdbcUtil.close(pstm1); JdbcUtil.close(rs1);
		return list2;
		
			
	}
	
	
	public int allCount(Connection con) throws SQLException {
	    String sql = "select count(cus_no) from custom";
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(sql);
	    while(rs.next()) {
	    	return rs.getInt(1);
	    }
		return -1;
	}
	
	
	public String boardnick(Connection con,String m_id) throws SQLException {
		 String sql = "select m_nickname from member where m_id=?";
		 PreparedStatement pstm = con.prepareStatement(sql);
		 pstm.setString(1, m_id);
		 ResultSet rs = pstm.executeQuery();
		 String nick=null;
		 
		 while(rs.next()) {
			nick =rs.getString(1);
			
		}
		 JdbcUtil.close(pstm);
		 JdbcUtil.close(rs);
		 if(nick==null) {
			 nick = "비회원";
		 }
		return nick;
		
	}
}