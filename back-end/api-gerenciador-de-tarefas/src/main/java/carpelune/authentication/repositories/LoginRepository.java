package carpelune.authentication.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carpelune.authentication.models.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, UUID> {
	
	public Login findByEmail(String email);
	
}
