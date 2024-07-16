package carpelune.workspaces.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record UpdateWorkspaceDTO(
		
		@NotBlank(message = "id is required")
		UUID id, 
		
		@NotBlank(message = "userId is required")
		UUID userId, 
		
		String title, 
		
		String description
){}
