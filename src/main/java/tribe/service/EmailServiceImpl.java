package tribe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tribe.service.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Value("${spring.mail.username}")
	protected String mailFrom;

	protected JavaMailSender emailSender;
	
	public EmailServiceImpl(JavaMailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(mailFrom);
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
	}

}
