package S_M_S_MailService.Dto;

import lombok.Data;

@Data
public class SubUserDTO {
    private String email;
    private String password;
    private String schoolID;
    private PermissionDto permissions;
}
