package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/mupdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전송온 값에 한글이 있을 경우, 인코딩 처리함
		request.setCharacterEncoding("utf-8");
		
		//2. 전송온 값을 꺼내서 변수 또는 객체에 저장하기
		Member member = new Member();
		member.setUserId(request.getParameter("userid"));
		member.setUserPwd(request.getParameter("userpwd"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		
		//취미는 하나의 문자열로 바꿔서 기록함
		member.setHobby(String.join(",", request.getParameterValues("hobby")));
		member.setEtc(request.getParameter("etc"));
		
		try {
			if(new MemberService().updateMember(member) > 0){
				response.sendRedirect("/second/index.jsp");
			}else{
				RequestDispatcher errorPage = 
						request.getRequestDispatcher(
							"views/member/memberError.jsp");
					request.setAttribute("message", "회원정보 수정 실패!");
					errorPage.forward(request, response);
			}
		} catch (MemberException e) {
			RequestDispatcher errorPage = 
				request.getRequestDispatcher(
					"views/member/memberError.jsp");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);
			
		}
	}

}







