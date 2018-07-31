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
 * Servlet implementation class MemberSelectServlet
 */
@WebServlet("/myinfo")
public class MemberSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전송온 값 꺼내서 변수에 저장
		String userId = request.getParameter("userid");
		
		RequestDispatcher view = null;
		try {
			Member member = 
				new MemberService().selectMember(userId);
			
			System.out.println("myinfo : " + member);
			
			if(member != null){
				view = request.getRequestDispatcher("views/member/myInfoView.jsp");
				request.setAttribute("member", member);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", userId + "에 대한 조회 실패!");
				view.forward(request, response);
			}
			
		} catch (MemberException e) {
			view = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
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
