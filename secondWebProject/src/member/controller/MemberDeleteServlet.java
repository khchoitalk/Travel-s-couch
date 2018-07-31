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

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mdelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userid");
		
		try {
			if(new MemberService().deleteMember(userId) > 0){
				response.sendRedirect("/second/logout");
			}else{
				RequestDispatcher errorPage = 
						request.getRequestDispatcher(
							"views/member/memberError.jsp");
					request.setAttribute("message", "회원정보 삭제 실패!");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
