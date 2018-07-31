package notice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/fdown")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 첨부파일 다운로드 처리용 컨트롤러
		//전송온 값에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("utf-8");
		
		//파일이 저장된 폴더 경로 지정
		String readFolder = request.getSession()
				.getServletContext().getRealPath("/nupfiles");
		
		String originalFileName = request.getParameter("ofile");
		String renameFileName = request.getParameter("rfile");
		
		//클라이언트로 내보낼 출력 스트림 생성
		ServletOutputStream downOut = 
				response.getOutputStream();
		
		//읽을 파일에 대한 File 객체 만들기
		File downFile = new File(
			readFolder + "/" + renameFileName);
		
		//파일의 내용을 읽어서, 읽은 데이터를 출력(내보냄)
		response.setContentType("text/plain; charset=utf-8");
		//다운로드시 파일명 설정하기
		response.addHeader("Content-Disposition", 
			"attachment; filename=\"" + 
			new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1")
			+ "\"");
		response.setContentLength((int)downFile.length());
		
		//파일 읽어서 내보냄
		BufferedInputStream bin = 
			new BufferedInputStream(
				new FileInputStream(downFile));
		int read = -1;
		while((read = bin.read()) != -1){
			downOut.write(read);
			downOut.flush();
		}
		
		downOut.close();
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
