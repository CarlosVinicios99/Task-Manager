package carpelune.workspaces.services;

import java.util.logging.Logger;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.users.models.UserWorkspace;
import carpelune.users.repositories.UsersWorkspacesRepository;
import carpelune.workspaces.dto.CreateWorkspaceDTO;
import carpelune.workspaces.dto.DeleteWorkspaceDTO;
import carpelune.workspaces.models.Workspace;
import carpelune.workspaces.repositories.WorkspacesRepository;

@Service
public class WorkspacesService {
	
	private Logger logger = Logger.getLogger(Workspace.class.getName());
	
	@Autowired
	private WorkspacesRepository workspacesRepository;
	
	@Autowired
	private UsersWorkspacesRepository usersWorkspacesRepository;
	
	
	public ResponseEntity<Workspace> createWorkspace(CreateWorkspaceDTO workspaceDTO){
		
		this.logger.log(Level.INFO, "creating a new workspace");
		
		try {
			Workspace newWorkspace = new Workspace();
			newWorkspace.setTitle(workspaceDTO.title());
			newWorkspace.setDescription(workspaceDTO.description());
			newWorkspace.setCreationTimestamp(Instant.now().toEpochMilli());
			
			this.logger.log(Level.WARNING, "saving workspace in the database");
			newWorkspace = this.workspacesRepository.save(newWorkspace);
			
			UserWorkspace userWorkspace = new UserWorkspace();
			userWorkspace.setUserId(workspaceDTO.userId());
			userWorkspace.setRole("ADMIN");
			userWorkspace.setWorkspaceId(newWorkspace.getId());
			
			this.logger.log(Level.WARNING, "saving the link between user and workspace in the database");
			this.usersWorkspacesRepository.save(userWorkspace);
			
			return ResponseEntity.status(HttpStatus.OK).body(newWorkspace);
			
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while registering workspace " + error.getMessage());			
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Workspace> findWorkspaceById(UUID workspaceId){
		
		this.logger.log(Level.INFO, "fetching workspace by ID");
		
		if(workspaceId == null || workspaceId.toString().isEmpty()) {
			this.logger.log(Level.WARNING, "the provided ID is undefined");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			this.logger.log(Level.WARNING, "fetching workspace from the database");
			Workspace searchedWorkspace = this.workspacesRepository.findById(workspaceId).get();
			
			if(searchedWorkspace == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing workspace");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(searchedWorkspace);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while fetching workspace by ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Void> deleteWorkspace(DeleteWorkspaceDTO deleteWorkspaceDTO){
		
		this.logger.log(Level.INFO, "deleting workspace");
		
		if(deleteWorkspaceDTO.userId() == null || deleteWorkspaceDTO.toString().isEmpty()) {
			this.logger.log(Level.INFO, "the provided user ID is undefined");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		if(deleteWorkspaceDTO.workspaceId() == null || deleteWorkspaceDTO.toString().isEmpty()) {
			this.logger.log(Level.INFO, "the provided workspace ID is undefined");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			this.logger.log(Level.WARNING, "deleting the link between user and workspace in the database");
			this.usersWorkspacesRepository
				.deleteByUserIdAndWorkspaceId(deleteWorkspaceDTO.userId(), deleteWorkspaceDTO.workspaceId());
			
			this.logger.log(Level.WARNING, "deleting workspace by ID");
			this.workspacesRepository.deleteById(deleteWorkspaceDTO.workspaceId());
			
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while deleting workspace by ID");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
}
