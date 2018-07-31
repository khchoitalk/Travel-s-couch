package notice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.exception.NoticeException;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/nupdate")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파일첨부 기능이 있는 공지사항 수정 처리용 컨트롤러

		RequestDispatcher view = null;
		// enctype="multipart/form-data" 로 전송되었는지 확인
		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/notice/noticeError.jsp");
			request.setAttribute("message", "enctype 속성에 multipart 사용안 함");
			view.forward(request, response);
		}

		// multipart 로 전송이 왔기 때문에 사용 못 함
		// request.setCharacterEncoding("utf-8");

		// 업로드할 파일의 용량 제한 : 예, 10Mbyte 로 정한다면
		int maxSize = 1024 * 1024 * 10;

		// 업로드되는 파일의 저장 폴더 지정하기
		// String savePath = "c:\\work\\save";
		// 현재 구동중인 애플리케이션 폴더 안에 저장시에는
		String savePath = request.getSession().getServletContext().getRealPath("/nupfiles");

		// cos.jar 라이브러리 사용한 경우
		// request 를 multipart request 객체로 변환함
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		// 객체 생성과 동시에 파일 업로드 처리됨

		// 전송된 공지 정보를 추출해서 객체에 저장 처리
		Notice notice = new Notice();
		notice.setNoticeNo(Integer.parseInt(mrequest.getParameter("no")));
		notice.setNoticeTitle(mrequest.getParameter("ntitle"));
		notice.setNoticeContent(mrequest.getParameter("ncontent"));

		// 새로운 첨부파일이 있다면
		// 저장폴더에 기록된 원래 파일명 조회
		String originalFileName = mrequest.getFilesystemName("upfile");
		if (originalFileName != null) {
			notice.setOriginalFilepath(originalFileName);

			// 업로드된 파일명을 "년월일시분초.확장자" 로 변경함
			if (originalFileName != null) {
				// 변경할 파일명 만들기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "."
						+ originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

				// 파일명 바꾸려면 File 객체의 renameTo() 사용함
				File originFile = new File(savePath + "\\" + originalFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);

				// 파일 이름바꾸기 실행 >> 실패할 경우 직접 바꾸기함
				// 새 파일만들고 원래 파일 내용 읽어서 복사하고
				// 복사가 끝나면 원래 파일 삭제함
				if (!originFile.renameTo(renameFile)) {
					int read = -1;
					byte[] buf = new byte[1024];

					FileInputStream fin = new FileInputStream(originFile);
					FileOutputStream fout = new FileOutputStream(renameFile);

					while ((read = fin.read(buf, 0, buf.length)) != -1) {
						fout.write(buf, 0, read);
					}

					fin.close();
					fout.close();
					// 원본 파일 삭제함
					originFile.delete();
				}

				notice.setRenameFilepath(renameFileName);
			}
			
			//저장되어 있는 이전 첨부파일은 삭제함
			String removeFileName = mrequest.getParameter("rfile");
			File removeFile = new File(savePath + "\\" + removeFileName);
			removeFile.delete();
		} // 새로운 첨부파일이 있을 때...

		try {
			if (new NoticeService().updateNotice(notice) > 0) {
				response.sendRedirect("/second/nlist");
			} else {
				view = request.getRequestDispatcher("views/notice/noticeError.jsp");
				request.setAttribute("message", "공지글 수정 실패");
				view.forward(request, response);
			}
		} catch (NoticeException e) {
			view = request.getRequestDispatcher("views/notice/noticeError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
