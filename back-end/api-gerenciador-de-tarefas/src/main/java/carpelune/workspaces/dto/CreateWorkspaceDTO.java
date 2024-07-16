package carpelune.workspaces.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CreateWorkspaceDTO(
		
		@NotBlank(message = "userId is required")
		UUID userId, 
		
		@NotBlank(message = "title is required")
		String title, 
		
		String description
) {

}
