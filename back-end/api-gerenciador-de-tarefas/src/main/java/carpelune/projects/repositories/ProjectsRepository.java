package carpelune.projects.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carpelune.projects.models.Project;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, UUID>{

}
