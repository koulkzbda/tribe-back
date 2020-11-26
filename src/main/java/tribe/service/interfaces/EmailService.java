package tribe.service.interfaces;

public interface EmailService {
	void sendSimpleMessage(String to,
            String subject,
            String text);
}
