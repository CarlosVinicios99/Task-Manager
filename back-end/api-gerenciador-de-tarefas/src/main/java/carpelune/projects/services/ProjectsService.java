package carpelune.projects.services;

import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.projects.dto.CreateProjectDTO;
import carpelune.projects.dto.DeleteProjectDTO;
import carpelune.projects.models.Project;
import carpelune.projects.repositories.ProjectsRepository;
import carpelune.users.repositories.UsersWorkspacesRepository;

@Service
public class ProjectsService {
	
	private Logger logger = Logger.getLogger(ProjectsService.class.getName());
	
	@Autowired
	private ProjectsRepository projectsRepository;
	
	@Autowired
	private UsersWorkspacesRepository usersWorkspacesRepository;
	
	
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
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while registering project");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Project> findProjectById(UUID projectId){
		
		this.logger.log(Level.INFO, "fetching project by ID");
		
		if(projectId == null || projectId.toString().isEmpty()) {
			this.logger.log(Level.WARNING, "the provided project ID is undefined");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			this.logger.log(Level.WARNING, "fetching project from the database");
			Project searchedProject = this.projectsRepository.findById(projectId).get();
			
			if(searchedProject == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing project");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(searchedProject);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while fetching project by ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	//TODO public ResponseEntity<UpdateProjectDTO> updateProject()
	
	public ResponseEntity<Void> deleteProject(DeleteProjectDTO deleteProjectDTO){
		
		this.logger.log(Level.INFO, "deleting project by ID");
		
		if(deleteProjectDTO.userId() == null || deleteProjectDTO.userId().toString().isEmpty()) {
			this.logger.log(Level.WARNING, "the provided user ID is undefined");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		if(deleteProjectDTO.projectId() == null || deleteProjectDTO.projectId().toString().isEmpty()) {
			this.logger.log(Level.WARNING, "the provided project ID is undefined");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			this.logger.log(Level.WARNING, "fetching the project from database");
			Project project = this.projectsRepository.findById(deleteProjectDTO.projectId()).get();
			
			if(project == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing project");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
			this.logger.log(Level.WARNING, "checking in the database if the user is an administrator of the workspace");
			Boolean isUserAdminOfWorkspace = 
				this.usersWorkspacesRepository.isUserAdminOfWorkspace(deleteProjectDTO.userId(), project.getWorkspaceId());
			
			if(!isUserAdminOfWorkspace) {
				this.logger.log(Level.WARNING, "user does not have permission to delete this project");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
			this.logger.log(Level.WARNING, "deleting project by ID");
			this.projectsRepository.deleteById(project.getId());
			
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while deleting project");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
}
