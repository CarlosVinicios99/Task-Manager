package carpelune.workspaces.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carpelune.workspaces.models.Workspace;

@Repository
public interface WorkspacesRepository extends JpaRepository<Workspace, UUID>{

}
