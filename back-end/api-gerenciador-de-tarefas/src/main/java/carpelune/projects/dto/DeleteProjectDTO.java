package carpelune.projects.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record DeleteProjectDTO(
	
	@NotBlank(message = "userId is required")
	UUID userId, 
	
	@NotBlank(message = "projectId is required")
	UUID projectId
){}
