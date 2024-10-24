package S_M_S_MailService.Service;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
