package S_M_S_MailService.Security.Repo;

import S_M_S_MailService.Security.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepo extends JpaRepository<Users , UUID> {
    Users findByEmail(String email);
}
