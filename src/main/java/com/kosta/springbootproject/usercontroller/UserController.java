package com.kosta.springbootproject.usercontroller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.springbootproject.userservice.EmailService;
import com.kosta.springbootproject.userservice.UserPageUserService;

@Controller
public class UserController {
	
	@Autowired
	UserPageUserService uservice;
	@Autowired
	private EmailService emailService;
	
	//>>userInsert 각종 체크
	@ResponseBody
	@PostMapping("/user/userIdChk")
	public int userIdChk(@RequestBody Map<String, String> userId) {
		int result;
		result = uservice.findMemberIdByUserId(userId.get("userId")).isEmpty()?0:1;
		return result;
	}
	
	//이메일인증
	@ResponseBody
	@PostMapping("/user/email/send")
	public void sendmail(String email, HttpServletRequest request) throws MessagingException {
		HttpSession session = request.getSession();
		System.out.println("왔니?");
		System.out.println(email);
		//코드 생성
		//난수 생성을 위한 랜덤 클래스
		Random random=new Random();  
		//인증번호 
		String key="";  
		//입력 키를 위한 코드
		for(int i =0; i<3;i++) {
			int index=random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
			key+=(char)index;
		}
		int numIndex=random.nextInt(9999)+1000; //4자리 랜덤 정수를 생성
		key+=numIndex;
		
		//본문 작성
		StringBuffer emailcontent = new StringBuffer();
		emailcontent.append("<!DOCTYPE html>");
		emailcontent.append("<html>");
		emailcontent.append("<head>");
		emailcontent.append("</head>");
		emailcontent.append("<body>");
		emailcontent.append(
					" <div"																																																		 
				+	"	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #02b875; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		 
				+	"	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															 
				+	"		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">YG1110 BLOG</span><br />"																													 
				+	"		<span style=\"color: #02b875\">메일인증</span> 안내입니다."																																				 
				+	"	</h1>\n"																																																 
				+	"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													 
				+	"		환영합니다.<br />"																																													 
				+	"		KOSTA에 가입해 주셔서 진심으로 감사드립니다.<br />"																																						 
				+	"		아래 <b style=\"color: #02b875\">'메일 인증'</b> 이메일 코드를 입력하여 회원가입을 완료해 주세요.<br />"																													 
				+	"		감사합니다."																																															 
				+	"	</p>"																																																	
				+	"	<p"																																																	
				+	"		style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							 
				+	"		인증 번호 :"
				+			key
				+	"	</p>"																																														
				+	"	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		
				+	" </div>"
		);
		emailcontent.append("</body>");
		emailcontent.append("</html>");
		emailService.checkMain(email, "[KOSTA 이메일 인증]", emailcontent.toString());
		System.out.println("보내기");
		System.out.println(key);
		session.setAttribute("key", key);
		System.out.println(session.getAttribute("key"));
	}
	
	@ResponseBody
	@PostMapping("/user/email/certificate")
	public Map<String, Object> emailCertificate(String authNum, HttpServletRequest request) {
		System.out.println(authNum);
		String emailKey="";
		String message="";
		String info="";
		boolean emailOk = false;
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		emailKey = (String)session.getAttribute("key");
		System.out.println(session.getAttribute("key"));
		if(authNum.equals("")){
			message = "메일 주소가 입력되지 않았습니다.";
			info = "warning";
		}
		else if (authNum.equals(emailKey)) {   //인증 키 값을 비교를 위해 텍스트인풋과 벨류를 비교
			message = "인증되었습니다";
			info = "success";
			emailOk = true;
		} else {
			message = "인증 실패";
			info = "warning";
		}
		map.put("message", message);
		map.put("info", info);
		map.put("emailOk", emailOk);
		session.removeAttribute("key");
		return map;
	}
	
	@ResponseBody
	@PostMapping("/user/email/end")
	public void deleteSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("key");
		
	}
}
