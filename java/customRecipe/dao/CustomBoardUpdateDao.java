package customRecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import customRecipe.dto.CustomBoardAddDto;
import customRecipe.dto.CustomBoardHashDto;
import jdbc.JdbcUtil;

public class CustomBoardUpdateDao {
	public void updateList(CustomBoardAddDto dto, Connection con,String num) throws SQLException {

		String sql = "update custom set m_id=?,cus_title=?,cus_content=? where cus_no=(select cus_no from custom_view where num=?)";
				


//		String cus_no = dto.getCus_no(); 오토키 null고정
//		String m_no = dto.getM_no(); 세션 회원id 
//		String c_no = dto.getC_no(); 제품id 커피리스트 
	
		String m_id = dto.getm_id();
		String cus_name =dto.getcus_name();
		String cus_content = dto.getcus_content();
//		String cus_regdate = dto.getCus_regdate();
//		String cus_sumgood = dto.getCus_sumgood();
	
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setString(1,m_id);
				pstm.setString(2,cus_name);
				pstm.setString(3,cus_content);
				pstm.setString(4,num);
				pstm.executeUpdate();
				
				
				

				JdbcUtil.close(pstm);

	}
	
	public int readCusNo(Connection con) throws SQLException {
		String sql = "select cus_no from custom order by cus_no desc limit 1";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int a = 0;
			while(rs.next()) {
				a = rs.getInt(1);
	}
			JdbcUtil.close(st); JdbcUtil.close(rs);
			return a;			
	}
public void updateimg(String num,CustomBoardAddDto dto,Connection con) throws SQLException {
		
		String fsql = "update custom_img set cus_img_real=?,cus_img_copy=? where cus_no=(select cus_no from custom_view where num=?)";
		
		String cus_img = dto.getcus_img();
		String cus_img_realname = dto.getcus_img_realname();
		if(dto.getcus_img_realname() == null) {
			cus_img_realname = "default.png";
		}
			PreparedStatement pstm = con.prepareStatement(fsql);
			pstm.setString(1, cus_img);
			pstm.setString(2, cus_img_realname);
			pstm.setString(3, num);
			pstm.executeUpdate();
			
			JdbcUtil.close(pstm);
	}
	
public void updatehash(String num,CustomBoardHashDto dto,Connection con) throws SQLException {
		
		String fsql = "update  custom_hash set CUS_HASH_SHOT=?,CUS_HASH_MILK=?,CUS_HASH_SYRUP=?,CUS_HASH_TOP=?,CUS_HASH_DECAF=? where cus_no=(select cus_no from custom_view where num=?)";
		String shot =dto.getshot();
		String milkType =dto.getmilkType();
		String syrupType =dto.getsyrupType();
		String toppingType =dto.gettoppingType();
		String decaffeinated =dto.getdecaffeinated();
			
			PreparedStatement pstm = con.prepareStatement(fsql);
			pstm.setString(1,shot);
			pstm.setString(2,milkType);
			pstm.setString(3,syrupType);
			pstm.setString(4,toppingType);
			pstm.setString(5,decaffeinated);
			pstm.setString(6, num);
			pstm.executeUpdate();
			
			JdbcUtil.close(pstm);
	}
}
