package carpelune.users.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import carpelune.users.models.UserWorkspace;

public interface UsersWorkspacesRepository extends JpaRepository<UserWorkspace, UUID>{

}
