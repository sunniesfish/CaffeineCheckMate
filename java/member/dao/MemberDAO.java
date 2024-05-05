package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.dto.MemberDTO;

public class MemberDAO {
	
// ------------------------insert-----------------------------------------	
	public String insert(MemberDTO memberDTO, Connection conn) {
		System.out.println("인서트들어옴");
	    String returnPage = null;
	    String query = "INSERT INTO ccm.member(M_ID, M_PASSWORD, M_NAME, M_NICKNAME, M_BIRTH, M_CREATEDATE, M_PHONENUMBER, M_MAIL, M_GENDER, M_ROLE, M_CANCEL, M_SNSYN) VALUES(?,?,?,?,?,DEFAULT,?,?,?,?,?,?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, memberDTO.getDtoID());
	        pstmt.setString(2, memberDTO.getDtoPW());
	        pstmt.setString(3, memberDTO.getDtoNAME());
	        pstmt.setString(4, memberDTO.getDtoNICKNAME());
	        pstmt.setString(5, memberDTO.getDtoSSN());
	        pstmt.setString(6, memberDTO.getDtoTEL());
	        pstmt.setString(7, memberDTO.getDtoEMAIL());
	        pstmt.setString(8, memberDTO.getDtoGENDER());
	        pstmt.setString(9, memberDTO.getDtoROLE());
	        pstmt.setString(10, memberDTO.getDtoCANCEL());
	        pstmt.setString(11, memberDTO.getDtoSNS());

	        int result = pstmt.executeUpdate();
	        if (result == 1) {
	            returnPage = "/main.do";
	            System.out.println("데이터 입력 완료!");
	            conn.commit();
	        } else {
	            returnPage = "/views/screens/joinRequest.jsp";
	            System.out.println("데이터 입력 실패!");
	            conn.rollback();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return returnPage;
	}   

	//------------------------------------select-------------------------------------------
	//기본 아이디(Primary Key) select method
	public MemberDTO selectID(MemberDTO memberDTO, Connection conn) {
	    String query = "SELECT * FROM ccm.member WHERE M_ID = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, memberDTO.getDtoID());
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                memberDTO.setDtoID(rs.getString("M_ID"));
	                memberDTO.setDtoPW(rs.getString("M_PASSWORD"));
	                memberDTO.setDtoNAME(rs.getString("M_NAME"));
	                memberDTO.setDtoSSN(rs.getString("M_BIRTH"));
	                memberDTO.setDtoEMAIL(rs.getString("M_MAIL"));
	                memberDTO.setDtoNICKNAME(rs.getString("M_NICKNAME"));
	                memberDTO.setDtoTEL(rs.getString("M_PHONENUMBER"));
	                memberDTO.setDtoGENDER(rs.getString("M_GENDER"));
	                memberDTO.setDtoSNS(rs.getString("M_SNSYN").equals("Y") ? "동의" : "거절");
	                System.out.println("멤버에 아이디있음 디티오에 정보저장");
	            } else {
	            	System.out.println("없는아이디");
	            	String backupQuery = "SELECT * FROM ccm.member_backup WHERE M_ID = ?";
	            	memberDTO.setDtoPRO("memberEmpty");
	                backupSelectID(memberDTO,backupQuery,conn);
	            }
	            if (conn != null) conn.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return memberDTO;
	}
	// member_backup 조회
	public MemberDTO backupSelectID(MemberDTO memberDTO,String backupQuery, Connection conn) {
	    System.out.println("백업셀렉들어옴");
		//String query = "SELECT * FROM ccm.member_backup WHERE M_ID = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(backupQuery)) {
	        pstmt.setString(1, memberDTO.getDtoID());
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                memberDTO.setDtoID(rs.getString("M_ID"));
	                memberDTO.setDtoPW(rs.getString("M_PASSWORD"));
	                memberDTO.setDtoNAME(rs.getString("M_NAME"));
	                memberDTO.setDtoSSN(rs.getString("M_BIRTH"));
	                memberDTO.setDtoEMAIL(rs.getString("M_MAIL"));
	                memberDTO.setDtoNICKNAME(rs.getString("M_NICKNAME"));
	                memberDTO.setDtoTEL(rs.getString("M_PHONENUMBER"));
	                memberDTO.setDtoGENDER(rs.getString("M_GENDER"));
	                memberDTO.setDtoSNS(rs.getString("M_SNSYN").equals("Y") ? "동의" : "거절");
	                memberDTO.setDtoDELETEDATE(rs.getTimestamp("DELETED_AT"));
	            } else {
	            	System.out.println("백업도아이디없음");
	                memberDTO.setDtoPRO("backupEmpty");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return memberDTO;
	}
	//중복체크 물음표 2개 select method
	public int checkSystem(String value, String query, Connection conn) {
	    System.out.println(value);
	    //"SELECT ccm.member.M_ID FROM ccm.member WHERE M_ID = ? UNION all "
	    //+ "SELECT ccm.member_backup.M_ID FROM ccm.member_backup WHERE M_ID = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, value);
	        pstmt.setString(2, value);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next() ? 1 : 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    } 
	}	
	//중복체크 물음표 1개 select method
	public int checkSystemOne(String value, String query, Connection conn) {
	    System.out.println("checkSystemOne 으로넘어온 검색할 컬럼벨류 "+value);
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, value);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next() ? 1 : 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    } 
	}
//	    finally {
//	        try {
//	            if (conn != null) conn.close();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
	    //return result;
	// 수정요청메소드
	//변경될 값 : value / pk조건 : id / 셀렉문 : query 
	public String update(String value, String id, String query, Connection conn) {
	    String returnPage = null;
	    int result = -1;
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, value);
	        pstmt.setString(2, id);
	        result = pstmt.executeUpdate();
	        if (result == 1) {
	            returnPage = "true";
	            conn.commit();
	        } else {
	            returnPage = "false";
	            conn.rollback();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return returnPage;
	}
	
	//---------------------------delete---------------------------------------
	    
		public boolean delete(String deleteID,String query,Connection conn) {
		    System.out.println("delete문들어옴");

		    boolean result = false;
		    int num = -1;
		    try {
		    	// 데이터 삭제
		        PreparedStatement pstmt = conn.prepareStatement(query);
		        pstmt.setString(1, deleteID);
		        num = pstmt.executeUpdate();
		        if (num > 0) { // 삭제된 행이 있으면 true, 없으면 false
		            System.out.println(" 삭제 행 있으면 = true");
		        	result = true;
		        	// 트랜잭션 커밋
		            conn.commit();
		        } else {
		        	System.out.println(" 삭제 행 없으면 = false");
		            result = false;
		        }
		        pstmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return result;
		}
		//회원탈퇴 메소드(삭제하면 db백업테이블로 이동되고 7일후 자동 삭제된다	
		    public boolean deleteMember(String deleteID,Connection conn) {
		        System.out.println("deleteMember문 들어옴");
		        boolean result = false;
		        int num = -1;
		        try {
		        	conn.createStatement().execute("SET AUTOCOMMIT = 0");
		            // 외래 키 검사 비활성화
		            conn.createStatement().execute("SET FOREIGN_KEY_CHECKS = 0");
		            // 데이터 삭제
		            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ccm.member WHERE M_ID = ?");
		            pstmt.setString(1, deleteID);
		            num = pstmt.executeUpdate();
		            // 외래 키 검사 활성화
		            conn.createStatement().execute("SET FOREIGN_KEY_CHECKS = 1");

		            if (num > 0) { // 삭제된 행이 있으면 true, 없으면 false
		                System.out.println("삭제멤버 행 있으면 = true");
		                result = true;
		                // 트랜잭션 커밋
		                conn.commit();
		            } else {
		                System.out.println("삭제멤버 행 없으면 = false");
		                result = false;
		            }
		            pstmt.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return result;
		    }

		public boolean deleteCancelSelect(String cancelID,String cancelPW,Connection conn) {
			boolean result = false;
		    try (PreparedStatement pstmt = conn.prepareStatement
		    		("select * from ccm.member_backup where M_ID = ?")) {
		        	pstmt.setString(1,cancelID);
		        try (ResultSet rs = pstmt.executeQuery()) {	        	
		        	if (rs.next()) {
		        		String dbid = rs.getString("M_ID");
		        		String dbpw = rs.getString("M_PASSWORD");
		        		if(cancelID.equals(dbid) && cancelPW.equals(dbpw) ) {		        			
		        			result = true;
		        		}else {
		        			result = false;
		        		}
		        	}else {
		        		result = false;
		        	}
		        }
		        pstmt.close();
		        conn.close();
		    }catch(SQLException e) {
		        e.printStackTrace();
		    }
			return result;
		}
		public String deleteCancel (String cancelID, Connection conn) throws SQLException {
			String returnPage = null;
			int result = -1;
			PreparedStatement pstmt = null;
			String sql = "insert into member (M_NO, M_ID, M_PASSWORD, M_NAME, M_NICKNAME, M_BIRTH, M_CREATEDATE, M_PHONENUMBER, M_MAIL, M_GENDER, M_ROLE, M_CANCEL, M_SNSYN) SELECT M_NO, M_ID, M_PASSWORD, M_NAME, M_NICKNAME, M_BIRTH, M_CREATEDATE, M_PHONENUMBER, M_MAIL, M_GENDER, M_ROLE, M_CANCEL, M_SNSYN FROM member_backup where member_backup.M_ID=?";
			    pstmt = conn.prepareStatement((sql));
			    pstmt.setString(1, cancelID);
			    result = pstmt.executeUpdate();
			    if (result == 1) {
		        		conn.commit();
		        		String query = "delete from member_backup where M_ID = ?";
		        		delete(cancelID,query,conn);
		        		returnPage = "/views/screens/okDeleteCancel.jsp";
		        	}else {
		        		returnPage = "false";
		        	}
			    return returnPage;
		}
		//-----------------------------------------------------------
		//관리자 여부 확인 (coffeeList 사용)
		public String adminOk(Connection conn, String memberId) throws SQLException {
			String adminOkSQL = "SELECT M_ROLE "
							  + "FROM MEMBER "
							  + "WHERE M_ID = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				pstmt = conn.prepareStatement(adminOkSQL);
				pstmt.setString(1, memberId);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					return rs.getString("M_ROLE");
				}
				return null; //명시적으로 null을 반환해야 컴파일 에러 안 뜸
			} finally {
				pstmt.close();
		        conn.close();
			}
		}
// ------------------------------아이디 비번 찾기---------------------------------
		public MemberDTO find(MemberDTO memberDTO, Connection conn) {
		    String query = "SELECT * FROM ccm.member WHERE M_MAIL = ?";
		    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setString(1, memberDTO.getDtoEMAIL());
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                memberDTO.setDtoID(rs.getString("M_ID"));
		                memberDTO.setDtoPW(rs.getString("M_PASSWORD"));
		                memberDTO.setDtoNAME(rs.getString("M_NAME"));
		                memberDTO.setDtoSSN(rs.getString("M_BIRTH"));
		                memberDTO.setDtoEMAIL(rs.getString("M_MAIL"));
		                memberDTO.setDtoNICKNAME(rs.getString("M_NICKNAME"));
		                memberDTO.setDtoTEL(rs.getString("M_PHONENUMBER"));
		                memberDTO.setDtoGENDER(rs.getString("M_GENDER"));
		                memberDTO.setDtoSNS(rs.getString("M_SNSYN").equals("Y") ? "동의" : "거절");
		                memberDTO.setDtoPRO("true");
		            } else {
		            	System.out.println("없는이메일");
		            	memberDTO.setDtoPRO("false");
		            }
		            if (conn != null) conn.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		    return memberDTO;
		}
}