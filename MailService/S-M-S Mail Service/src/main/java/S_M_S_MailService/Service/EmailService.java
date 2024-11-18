package S_M_S_MailService.Service;

import S_M_S_MailService.Dto.PermissionDto;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
    void sendResetPassword(String to, String subject, String text , String name) throws IOException, MessagingException;
    void userCreationMail(String to, String subject, String text , String name , PermissionDto permissionDto) throws IOException, MessagingException;

}
