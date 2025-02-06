package carpelune.users.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import carpelune.authentication.models.Login;
import carpelune.users.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
	
	public UserDetails findByLogin(Login login);
	
	public User findByLoginId(UUID loginId);
	
}
