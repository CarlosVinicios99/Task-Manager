package carpelune.projects.services;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.projects.dto.CreateProjectDTO;
import carpelune.projects.models.Project;
import carpelune.projects.repositories.ProjectsRepository;

@Service
public class ProjectsService {
	
	private Logger logger = Logger.getLogger(ProjectsService.class.getName());
	
	@Autowired
	private ProjectsRepository projectsRepository;
	
	
	public ResponseEntity<Project> createProject(CreateProjectDTO createProjectDTO){
		
		this.logger.log(Level.INFO, "creating a new project");
		if(createProjectDTO.workspaceId() != null || createProjectDTO.workspaceId().toString().isEmpty()) {
			this.logger.log(Level.WARNING, "the provided workspace ID is undefined");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		
		try {
			Project newProject = new Project();
			newProject.setTitle(createProjectDTO.title());
			newProject.setDescription(createProjectDTO.description());
			newProject.setWorkspaceId(createProjectDTO.workspaceId());
			newProject.setCreationTimestamp(Instant.now().toEpochMilli());
			
			this.logger.log(Level.WARNING, "saving the new project in the database");
			newProject = this.projectsRepository.save(newProject);
			
			return ResponseEntity.status(HttpStatus.OK).body(newProject);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while registering project");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	//TODO public ResponseEntity<FindProjectDTO> findProjectById()
	//TODO public ResponseEntity<UpdateProjectDTO> updateProject()
	//TODO public ResponseEntity<Void> deleteProject()
	
}
