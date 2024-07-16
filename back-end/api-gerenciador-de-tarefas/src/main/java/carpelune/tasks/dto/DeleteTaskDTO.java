package carpelune.tasks.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record DeleteTaskDTO(
	
	@NotBlank(message = "id is required")
	UUID id, 
	
	@NotBlank(message = "creatorId is required")
	UUID creatorId
){}
