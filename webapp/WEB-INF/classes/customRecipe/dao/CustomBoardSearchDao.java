package customRecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import customRecipe.dto.CustomBoardListDto;
import jdbc.JdbcUtil;

public class CustomBoardSearchDao {
	
	
	//내용으로검색
public ArrayList<CustomBoardListDto> searchlist(String content,Connection con) throws SQLException {
	String sql = "select * from custom_view where CUS_CONTENT like ?";
	
	
	PreparedStatement pstm = con.prepareStatement(sql);
	CustomBoardListDao dao = new CustomBoardListDao();
	
	ArrayList<CustomBoardListDto> list = new ArrayList<>();

	pstm.setString(1,"%"+content+"%");
	ResultSet rs = pstm.executeQuery();
	while(rs.next()) {
			
		CustomBoardListDto dto = new CustomBoardListDto();
			
			dto.setCUS_NUM(rs.getInt(1));
			dto.setCUS_NO(rs.getInt(2));
			dto.setm_id(rs.getString(3));
			dto.setc_no(rs.getString(4));
			dto.setcus_title(rs.getString(5));
			dto.setCUS_CONTENT(rs.getString(6));
			dto.setCUS_REGDATE(rs.getString(7));
			dto.setCUS_SUMGOOD(rs.getString(8));
			
			dao.getimg(dto,con);
			dao.viewhash(dto,con);
			
			
			list.add(dto);
		}
		JdbcUtil.close(rs); JdbcUtil.close(pstm);
		return list;
	}


//해시태그-글번호로 검색
public ArrayList<CustomBoardListDto> searchhash(String cus_no,Connection con) throws SQLException {
	String sql = "select * from custom_view where cus_no = ? order by num desc";
	
	
	PreparedStatement pstm = con.prepareStatement(sql);
	CustomBoardListDao dao = new CustomBoardListDao();
	
	ArrayList<CustomBoardListDto> list = new ArrayList<>();

	pstm.setString(1,cus_no);
	ResultSet rs = pstm.executeQuery();
	while(rs.next()) {
			
		CustomBoardListDto dto = new CustomBoardListDto();
			
			dto.setCUS_NUM(rs.getInt(1));
			dto.setCUS_NO(rs.getInt(2));
			dto.setm_id(rs.getString(3));
			dto.setc_no(rs.getString(4));
			dto.setcus_title(rs.getString(5));
			dto.setCUS_CONTENT(rs.getString(6));
			dto.setCUS_REGDATE(rs.getString(7));
			dto.setCUS_SUMGOOD(rs.getString(8));
			
			dao.getimg(dto,con);
			dao.viewhash(dto,con);
			
			
			list.add(dto);
		}
		JdbcUtil.close(rs); JdbcUtil.close(pstm);
		return list;
	
	}

}
