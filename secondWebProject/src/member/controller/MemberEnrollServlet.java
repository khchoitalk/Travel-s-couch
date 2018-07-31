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
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/menroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 전송값에 한글이 포함되어 있다면
		//반드시 문자 인코딩 처리함
		request.setCharacterEncoding("utf-8");
		
		//2. 전송값 꺼내서, 변수 또는 객체에 저장하기
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		String userName = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String etc = request.getParameter("etc");
		//하나의 이름으로 여러 개의 값이 전송이 올 경우에는
		String[] hobbies = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbies);
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		member.setUserName(userName);
		member.setAge(age);
		member.setEmail(email);
		member.setPhone(phone);
		member.setGender(gender);
		member.setEtc(etc);
		member.setHobby(hobby);
		
		System.out.println(member.toString());
		
		response.setContentType("text/html; charset=utf-8");
		try {				
			if(new MemberService().insertMember(member) > 0){
				//회원 가입이 성공했을 때				
				response.sendRedirect("/second/index.jsp");
			}else{
				RequestDispatcher errorPage = 
					request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", "회원 가입 실패!");
				errorPage.forward(request, response);
			}
		} catch (MemberException e) {
			RequestDispatcher errorPage = 
					request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
