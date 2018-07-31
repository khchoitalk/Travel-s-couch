package member.model.service;

import java.sql.*;
import java.util.ArrayList;

import static common.JDBCTemplate.*;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.exception.MemberException;

public class MemberService {
	public MemberService(){}
	
	//로그인 처리용
	public String loginCheck(
			String userId, String userPwd) 
			throws MemberException {
		Connection conn = getConnection();
		String userName = 
			new MemberDao().loginCheck(conn, 
					userId, userPwd);
		close(conn);
		return userName;
	}

	public int insertMember(Member member) 
		throws MemberException {
		Connection con = getConnection();
		int result = new MemberDao().insertMember(con, member);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public Member selectMember(String userId) 
		throws MemberException {
		Connection con = getConnection();
		Member member = new MemberDao()
				.selectMember(con, userId);
		close(con);
		return member;
	}

	public int updateMember(Member member) 
		throws MemberException {
		Connection con = getConnection();
		int result = new MemberDao().updateMember(con, member);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	public int deleteMember(String userId)
		throws MemberException {
		Connection con = getConnection();
		int result = new MemberDao().deleteMember(con, userId);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public ArrayList<Member> selectList() 
		throws MemberException {
		Connection con = getConnection();
		ArrayList<Member> list = 
				new MemberDao().selectList(con);
		close(con);
		return list;
	}
}











