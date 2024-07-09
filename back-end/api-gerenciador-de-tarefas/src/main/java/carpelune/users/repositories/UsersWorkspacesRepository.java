package carpelune.users.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import carpelune.users.models.UserWorkspace;

public interface UsersWorkspacesRepository extends JpaRepository<UserWorkspace, UUID>{
	
	Optional<UserWorkspace> findByUserIdAndWorkspaceId(UUID userId, UUID workspaceId);
	
	void deleteByUserIdAndWorkspaceId(UUID userId, UUID workspaceId);
	
	 @Query(
        """
	 		SELECT CASE WHEN COUNT(uw) > 0 THEN TRUE ELSE FALSE END
	 		FROM UserWorkspace uw
	 		WHERE uw.userId = :userId AND uw.workspaceId = :workspaceId AND uw.role = 'ADMIN'
	 	""" 
	 )
	 boolean isUserAdminOfWorkspace(@Param("userId") UUID userId, @Param("workspaceId") UUID workspaceId);
}
