package carpelune.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carpelune.users.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

}
