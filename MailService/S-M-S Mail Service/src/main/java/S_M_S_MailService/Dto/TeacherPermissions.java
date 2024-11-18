package S_M_S_MailService.Dto;

import lombok.Data;

@Data
public class TeacherPermissions {
    private boolean viewTeacher;
    private boolean createTeacher;
    private boolean updateTeacher;
    private boolean deleteTeacher;
    private boolean assignSubject;
    public TeacherPermissions (){
        this.viewTeacher = false;
        this.createTeacher = false;
        this.updateTeacher = false;
        this.deleteTeacher = false;
        this.assignSubject = false;
    }
}
