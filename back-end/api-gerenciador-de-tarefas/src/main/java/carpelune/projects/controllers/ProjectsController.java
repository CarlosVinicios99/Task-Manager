package carpelune.projects.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carpelune.projects.dto.CreateProjectDTO;
import carpelune.projects.dto.DeleteProjectDTO;
import carpelune.projects.dto.UpdateProjectDTO;
import carpelune.projects.models.Project;
import carpelune.projects.services.ProjectsService;
import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/projects")
@RestController
public class ProjectsController {
	
	@Autowired
	private ProjectsService projectsService;
	
	
	@PostMapping()
	public ResponseEntity<Project> createProject(@RequestBody @Valid CreateProjectDTO createProjectDTO){
		return this.projectsService.createProject(createProjectDTO);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<Project> findProjectById(@PathVariable UUID projectId){
		return this.projectsService.findProjectById(projectId);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateProject(@RequestBody @Valid UpdateProjectDTO updateTaskDTO){
		return this.projectsService.updateProject(updateTaskDTO);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteProject(@RequestBody @Valid DeleteProjectDTO deleteTaskDTO){
		return this.projectsService.deleteProject(deleteTaskDTO);
	}
}
