package carpelune.users.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carpelune.users.models.UserWorkspaceView;

@Repository
public interface UsersWorkspacesViewRepository extends JpaRepository<UserWorkspaceView, UUID> {
	
	public Page<UserWorkspaceView> findByWorkspaceId(UUID workspaceId, Pageable pageable);
}
