package S_M_S_MailService.Security;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class Users {
    private UUID id;
    private LocalDateTime creationDateTime ;
    private String email;
    private String password;

}
