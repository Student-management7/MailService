package S_M_S_MailService.Controller;

import S_M_S_MailService.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MailController {

    @Autowired
    private EmailService emailService;



    @GetMapping("/sendMail")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendSimpleEmail(to, subject, text);
        return "Email sent successfully";
    }
    @GetMapping("/send-reset-PasswordMail")
    public String newPasswordMail(@RequestParam String to, @RequestParam String subject, @RequestParam String text , @RequestParam String name) {
        try {
            emailService.sendResetPassword(to, subject, text , name);
        } catch (
                Exception e) {
           return "Error while sending mail for reset password :  " + e.getMessage();
        }
        return "Email sent successfully";
    }

    @GetMapping("/test")
    public  String hello(){
        return "hello bro from mail service";
    }
}
