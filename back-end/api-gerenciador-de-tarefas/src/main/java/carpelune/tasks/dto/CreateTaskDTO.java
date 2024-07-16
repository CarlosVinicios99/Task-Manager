package carpelune.tasks.dto;

import java.util.UUID;

import carpelune.tasks.models.PriorityLevel;
import carpelune.tasks.models.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record CreateTaskDTO(
	
	@NotBlank(message = "title is required")
	String title, 
	
	@NotBlank(message = "status is required")
	TaskStatus status, 
	
	@NotBlank(message = "priority is required")
	PriorityLevel priority, 
	
	String description,
	
	
	Long expectedConclusionTimestamp, 
	
	@NotBlank(message = "creatorId is required")
	UUID creatorId, 
	
	UUID workerId, 
	
	@NotBlank(message = "projectId is required")
	UUID projectId
){}
