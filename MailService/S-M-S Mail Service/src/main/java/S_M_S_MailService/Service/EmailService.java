package S_M_S_MailService.Service;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
    void sendResetPassword(String to, String subject, String text , String name) throws IOException, MessagingException;
}
