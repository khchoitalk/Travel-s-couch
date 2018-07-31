package member.model.dao;

import java.sql.*;
import java.util.ArrayList;

import static common.JDBCTemplate.*;
import member.exception.MemberException;
import member.model.vo.Member;

public class MemberDao {
	public MemberDao(){}
	
	//로그인 처리용
	public String loginCheck(Connection con, 
			String userId, String userPwd) 
		throws MemberException {
		String userName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		
		String query = "select username from member "
				+ "where userid = ? and userpwd = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				userName = rset.getString("username");
			}else
				throw new MemberException("로그인 조회 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return userName;
	}

	public int insertMember(Connection con, 
			Member member) 
				throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into member values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "sysdate, sysdate)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getHobby());
			pstmt.setString(9, member.getEtc());
						
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MemberException("회원 가입 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection con, 
			String userId) 
		throws MemberException {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member where userid = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				//조회해 온 행의 각 컬럼값을 꺼내서
				//객체의 필드에 기록 저장함
				member = new Member();
				
				member.setUserId(userId);
				member.setUserPwd(rset.getString("userpwd"));
				member.setUserName(rset.getString("username"));
				member.setAge(rset.getInt("age"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setHobby(rset.getString("hobby"));
				member.setEtc(rset.getString("etc"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModifiedDate(rset.getDate("LASTMODIFIED"));		

			}else
				throw new MemberException(userId + "에 대한 행이 없음!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return member;
	}
	
	public int updateMember(Connection con, 
			Member member) 
					throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member "
				+ "set userpwd = ?, age = ?, "
				+ "email = ?, phone = ?, "
				+ "hobby = ?, etc = ? "
				+ "where userid = ?";
		
		try {
			pstmt = con.prepareStatement(query);			
			pstmt.setString(1, member.getUserPwd());			
			pstmt.setInt(2, member.getAge());			
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getEtc());
			pstmt.setString(7, member.getUserId());
						
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MemberException("회원 정보 수정 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteMember(Connection con, 
			String userId) throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from member "
				+ "where userid = ?";
		
		try {
			pstmt = con.prepareStatement(query);				
			pstmt.setString(1, userId);
						
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MemberException("회원 정보 삭제 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Member> selectList(
			Connection con) 
					throws MemberException {
		ArrayList<Member> list = new ArrayList<Member>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from member";
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				Member member = new Member();
				
				member.setUserId(rset.getString("userid"));
				member.setUserPwd(rset.getString("userpwd"));
				member.setUserName(rset.getString("username"));
				member.setAge(rset.getInt("age"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setHobby(rset.getString("hobby"));
				member.setEtc(rset.getString("etc"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setLastModifiedDate(rset.getDate("LASTMODIFIED"));		

				list.add(member);
			}
			
			if(list.size() == 0)
				throw new MemberException(
						"회원 정보가 존재하지 않습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException(e.getMessage());
		}finally{
			close(rset);
			close(stmt);
		}
		
		return list;
	}
}







