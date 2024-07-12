package carpelune.users.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
	
	@NotBlank(message = "user ID is required")
	UUID id, 
	
	String name
){}
