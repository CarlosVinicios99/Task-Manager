package carpelune.workspaces.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carpelune.workspaces.dto.CreateWorkspaceDTO;
import carpelune.workspaces.dto.DeleteWorkspaceDTO;
import carpelune.workspaces.dto.UpdateWorkspaceDTO;
import carpelune.workspaces.models.Workspace;
import carpelune.workspaces.services.WorkspacesService;

@CrossOrigin
@RestController
@RequestMapping("/workspaces")
public class WorkspacesController {
	
	@Autowired
	private WorkspacesService workspacesService;
	
	
	@PostMapping()
	public ResponseEntity<Workspace> createWorkspace(CreateWorkspaceDTO createWorkspaceDTO){
		return this.workspacesService.createWorkspace(createWorkspaceDTO);
	}
	
	@GetMapping("/{workspaceId}")
	public ResponseEntity<Workspace> findWorkspaceById(UUID workspaceId){
		return this.workspacesService.findWorkspaceById(workspaceId);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateWorkspace(UpdateWorkspaceDTO updateWorkspaceDTO){
		return this.workspacesService.updateWorkspace(updateWorkspaceDTO);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteWorkspace(DeleteWorkspaceDTO deleteWorkspaceDTO){
		return this.workspacesService.deleteWorkspace(deleteWorkspaceDTO);
	}
}
