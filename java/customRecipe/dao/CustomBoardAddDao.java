package customRecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import customRecipe.dto.CustomBoardAddDto;
import customRecipe.dto.CustomBoardHashDto;
import jdbc.JdbcUtil;


 
public class CustomBoardAddDao {

	

	public void addList(CustomBoardAddDto dto, Connection con) throws SQLException {

		String sql = "insert into custom(cus_no,m_id,c_no,cus_title,cus_content,cus_regdate,cus_sumgood)"
		+ "values(null,?,1,?,?,default,0)";


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
				pstm.executeUpdate();
				
				
				

				JdbcUtil.close(pstm);

	}
	

	//작성된 게시글번호 조회
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
	
	//이미지 테이블에 이미지파일 업로드
	public void addimg(int num,CustomBoardAddDto dto,Connection con) throws SQLException {
		
		String fsql = "insert into custom_img(cus_img_no,cus_no,CUS_IMG_REAL,CUS_IMG_COPY) values(null,?,?,?)";
		
		String cus_img = dto.getcus_img();
		String cus_img_realname = dto.getcus_img_realname();
		if(dto.getcus_img_realname() == null) {
			cus_img_realname = "default.png";
		}
			PreparedStatement pstm = con.prepareStatement(fsql);
			pstm.setInt(1, num);
			pstm.setString(2, cus_img);
			pstm.setString(3, cus_img_realname);
			pstm.executeUpdate();
			
			JdbcUtil.close(pstm);
	}
	
public void addhash(int num,CustomBoardHashDto dto,Connection con) throws SQLException {
		
		String fsql = "insert into "
				+ "custom_hash(CUS_HASH_NO,CUS_NO,CUS_HASH_SHOT,CUS_HASH_MILK,CUS_HASH_SYRUP,CUS_HASH_TOP,CUS_HASH_DECAF)"
				+ " values(null,?,?,?,?,?,?)";
		String shot =dto.getshot();
		String milkType =dto.getmilkType();
		String syrupType =dto.getsyrupType();
		String toppingType =dto.gettoppingType();
		String decaffeinated =dto.getdecaffeinated();
			
		
			PreparedStatement pstm = con.prepareStatement(fsql);
			pstm.setInt(1, num);
			pstm.setString(2,shot);
			pstm.setString(3,milkType);
			pstm.setString(4,syrupType);
			pstm.setString(5,toppingType);
			pstm.setString(6,decaffeinated);
			pstm.executeUpdate();
			
			JdbcUtil.close(pstm);
	}
}
