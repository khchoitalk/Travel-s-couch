package email.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailSendServlet
 */
@WebServlet("/send")
public class EmailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties props = System.getProperties();
		 props.put("mail.smtp.user", "rlruddnr828@gmail.com");
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.port", "465");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.socketFactory.port", "465");
		 props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 props.put("mail.smtp.socketFactory.fallback", "false");
		 
		 Authenticator auth = new MyAuthentication();
		 
		 //session 생성 및 MimeMessage생성
		 
		 Session session = Session.getDefaultInstance(props, auth);
		 MimeMessage msg = new MimeMessage(session);
		 
		 try {
			 //편지 보낸 시간
			msg.setSentDate(new Date());
			InternetAddress from = new InternetAddress("rlruddnr828@gmail.com");
			
			//이메일 발신자
			msg.setFrom(from);
			
			//이메일 수신자
			String email = request.getParameter("receiver"); //사용자가 입력한 이멜 주소 받아오기
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			//이메일 제목
			msg.setSubject("Travel's couch 이메일 인증번호","UTF-8");
			
			//이메일 내용
			String code = request.getParameter("code_check"); //인증값 받기
			request.setAttribute("code", code);
			msg.setText(code + "<< 이메일 인증번호를 입력하시고 다양한 사람들과 여행을 함께하세요", "UTF-8");
			
			//이메일 헤더
			msg.setHeader("content-Type", "text/html");
			javax.mail.Transport.send(msg);
			System.out.println("보냄");
			
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e2) {
			e2.printStackTrace();
		}
		 
		 RequestDispatcher rd = request.getRequestDispatcher("/checkcode.jsp");
		 rd.forward(request, response);
		 
	}

}

		class MyAuthentication extends Authenticator {
			
			PasswordAuthentication pa;
			
			public MyAuthentication(){
				
				String id = "rlruddnr828@gmail.com"; // 구글 id
				String pw = "ruddnr4657!"; //구글 비밀번호
				
				//ID와 비밀번호를 입력한다.
				pa = new PasswordAuthentication(id, pw);
						
			}
			
			// 시스템에서 사용하는 인증정보
			public PasswordAuthentication getPasswordAuthentication() {
				return pa;
			}
		}
