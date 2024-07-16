package carpelune.workspaces.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record DeleteWorkspaceDTO(
	
	@NotBlank(message = "workspaceId is required")
	UUID workspaceId, 
	
	@NotBlank(message = "userId is required")
	UUID userId
){}
