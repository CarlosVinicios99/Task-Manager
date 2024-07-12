package carpelune.users.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
		
	@NotBlank(message = "name is required")
	String name,
		
	@NotBlank(message = "email is required")
	String email,
		
	@NotBlank(message = "password is required")
	String password, 
		
	@NotBlank(message = "password confirmed is required")
	String confirmPassword
){}
