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
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/nlist")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지사항 전체 글 조회 처리용 컨트롤러
		
		//내보낼 데이터에 한글이 있다면
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		try {
			ArrayList<Notice> noticeList = 
				new NoticeService().selectList();
			
			if(noticeList.size() > 0){
				view = request.getRequestDispatcher(
						"views/notice/noticeListView.jsp");
				request.setAttribute("noticeList", noticeList);
				view.forward(request, response);
			}else{
				view = request.getRequestDispatcher(
						"views/notice/noticeError.jsp");
				request.setAttribute("message", "공지사항 글이 없습니다.");
				view.forward(request, response);
			}
			
		} catch (NoticeException e) {
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
