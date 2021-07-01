package com.kosta.springbootproject.userservice;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
	
	public void checkMain(String email, String subject, String contents) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
      
		message.setTo(email); //스크립트에서 보낸 메일을 받을 사용자 이메일 주소 
		message.setSubject(subject);
		message.setText(contents, true);
		
		javaMailSender.send(mimeMessage);
	}

}
