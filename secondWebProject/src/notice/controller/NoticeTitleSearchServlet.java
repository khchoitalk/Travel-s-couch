package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.exception.NoticeException;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeTitleSearchServlet
 */
@WebServlet("/nsearch")
public class NoticeTitleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeTitleSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 제목 검색 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		
		RequestDispatcher view = null;
		try{
			ArrayList<Notice> list = 
				new NoticeService().selectTitle(keyword);
			
			if(list.size() > 0){
				view = request.getRequestDispatcher(
						"views/notice/noticeListView.jsp");
				request.setAttribute("noticeList", list);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher(
						"views/notice/noticeError.jsp");
				request.setAttribute("message", 
						keyword + "에 해당하는 제목글이 존재하지 않습니다.");
				view.forward(request, response);
			}
		}catch(NoticeException e){
			view = request.getRequestDispatcher(
					"views/notice/noticeError.jsp");
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
