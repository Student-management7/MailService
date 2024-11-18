package S_M_S_MailService.ServiceImpl;

import S_M_S_MailService.Dto.PermissionDto;
import S_M_S_MailService.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(fromEmail);

        mailSender.send(message);
    }

    public void sendResetPassword(String to, String subject, String text , String name) throws IOException, MessagingException {
        String content = loadTemplate("src/main/java/S_M_S_MailService/EmailTemplate/New-Reset-password.html" , name , text);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage , true);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setSentDate(new Date());
        helper.setText(content ,true);
        mailSender.send(mimeMessage);

    }

    @Override
    public void userCreationMail(String to, String subject, String text, String name, PermissionDto permissionDto) throws IOException, MessagingException {
        String content = loadUserCreationTemplate("src/main/java/S_M_S_MailService/EmailTemplate/UserCreationTemplate.html" , name , text , permissionDto);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage , true);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setSentDate(new Date());
        helper.setText(content ,true);
        mailSender.send(mimeMessage);
    }
    private String loadUserCreationTemplate(String fileName, String name, String newPassword, PermissionDto permissionDto) throws IOException {
        // Read the HTML template file
        String content = new String(Files.readAllBytes(Paths.get(fileName)));

        // Replace user-specific details
        content =content.replace("[Subuser's Name]", "User");
        content = content.replace("[username]", name);
        content = content.replace("[password]", newPassword);

        // Replace permissions dynamically
        content = content.replace("{{View Student P}}", permissionDto.getStudent().isViewStudent() ? "Yes" : "No");
        content = content.replace("{{Create Student P}}", permissionDto.getStudent().isCreateStudent() ? "Yes" : "No");
        content = content.replace("{{Update Student P}}", permissionDto.getStudent().isUpdateStudent() ? "Yes" : "No");
        content = content.replace("{{Delete Student P}}", permissionDto.getStudent().isDeleteStudent() ? "Yes" : "No");

        content = content.replace("{{View Attendance P}}", permissionDto.getAttendance().getViewAttendance() != null ? permissionDto.getAttendance().getViewAttendance() : "No");
        content = content.replace("{{Mark Attendance P}}", permissionDto.getAttendance().getMarkAttendance() != null ? permissionDto.getAttendance().getMarkAttendance() : "No");

        content = content.replace("{{View Fees P}}", permissionDto.getFees().isViewFees() ? "Yes" : "No");
        content = content.replace("{{Collect Fees P}}", permissionDto.getFees().isCollectFees() ? "Yes" : "No");

        content = content.replace("{{View Teacher P}}", permissionDto.getTeacher().isViewTeacher() ? "Yes" : "No");
        content = content.replace("{{Create Teacher P}}", permissionDto.getTeacher().isCreateTeacher() ? "Yes" : "No");
        content = content.replace("{{Update Teacher P}}", permissionDto.getTeacher().isUpdateTeacher() ? "Yes" : "No");
        content = content.replace("{{Delete Teacher P}}", permissionDto.getTeacher().isDeleteTeacher() ? "Yes" : "No");
        content = content.replace("{{Assign Subject P}}", permissionDto.getTeacher().isAssignSubject() ? "Yes" : "No");

        content = content.replace("{{View Course P}}", permissionDto.getCourse().isViewCourse() ? "Yes" : "No");
        content = content.replace("{{Create Course P}}", permissionDto.getCourse().isCreateCourse() ? "Yes" : "No");
        content = content.replace("{{Update Course P}}", permissionDto.getCourse().isUpdateCourse() ? "Yes" : "No");
        content = content.replace("{{Delete Course P}}", permissionDto.getCourse().isDeleteCourse() ? "Yes" : "No");

        content = content.replace("{{Schedule Exam P}}", permissionDto.getExam().isScheduleExam() ? "Yes" : "No");
        content = content.replace("{{View Result P}}", permissionDto.getExam().isViewResult() ? "Yes" : "No");

        content = content.replace("{{View Events P}}", permissionDto.getEvent().isViewEvents() ? "Yes" : "No");
        content = content.replace("{{Create Event P}}", permissionDto.getEvent().isCreateEvent() ? "Yes" : "No");
        content = content.replace("{{Update Event P}}", permissionDto.getEvent().isUpdateEvent() ? "Yes" : "No");
        content = content.replace("{{Delete Event P}}", permissionDto.getEvent().isDeleteEvent() ? "Yes" : "No");

        content = content.replace("{{View Reports P}}", permissionDto.getReport().isViewReports() ? "Yes" : "No");
        content = content.replace("{{Generate Reports P}}", permissionDto.getReport().isGenerateReports() ? "Yes" : "No");
        content = content.replace("{{Export Reports P}}", permissionDto.getReport().isExportReports() ? "Yes" : "No");

        return content;
    }
    private String loadTemplate(String fileName, String name, String newPassword) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        content = content.replace("{{name}}", name);
        content = content.replace("{{newPassword}}", newPassword);
        return content;
    }
}
