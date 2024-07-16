package carpelune.projects.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectDTO(
	
	@NotBlank(message = "title is required")
	String title, 
	
	String description, 
	
	@NotBlank(message = "workspaceId is required")
	UUID workspaceId
){}
