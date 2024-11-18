package S_M_S_MailService.FeignService;


import S_M_S_MailService.Dto.UserDto;
import S_M_S_MailService.Security.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "database-service")
public interface DatabaseService {

    @GetMapping("/user/getByEmail")
    public Users findByMail(@RequestParam String email);

    @PostMapping("/user/save")
    public Users saveUser(@RequestBody UserDto userDto);

    @PostMapping("/user/update")
    public Users updateUser(@RequestBody UserDto userDto);
}
