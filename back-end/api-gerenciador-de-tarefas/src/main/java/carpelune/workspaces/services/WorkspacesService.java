package carpelune.workspaces.services;

import java.util.logging.Logger;
import java.time.Instant;
import java.util.logging.Level;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.workspaces.dto.CreateWorkspaceDTO;
import carpelune.workspaces.models.Workspace;
import carpelune.workspaces.repositories.WorkspacesRepository;

@Service
public class WorkspacesService {
	
	private Logger logger = Logger.getLogger(Workspace.class.getName());
	
	@Autowired
	private WorkspacesRepository workspacesRepository;
	
	
	public ResponseEntity<Void> createWorkspace(CreateWorkspaceDTO workspaceDTO){
		
		this.logger.log(Level.INFO, "creating a new workspace");
		
		try {
			Workspace newWorkspace = new Workspace();
			newWorkspace.setTitle(workspaceDTO.title());
			newWorkspace.setDescription(workspaceDTO.description());
			newWorkspace.setCreationTimestamp(Instant.now().toEpochMilli());
			
			this.logger.log(Level.WARNING, "saving workspace in the database");
			this.workspacesRepository.save(newWorkspace);
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while registering workspace " + error.getMessage());			
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	/*
	public ResponseEntity<FindWorkspaceDTO> findWorkspaceById(){
		
		this.logger.log(Level.INFO, "");
	}
	*/
	
}
