package carpelune.users.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersWorkspacesViewRepository extends JpaRepository<UsersWorkspacesViewRepository, UUID> {

}
