package carpelune.tasks.dto;

import java.util.UUID;

import carpelune.tasks.models.PriorityLevel;
import carpelune.tasks.models.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateTaskDTO(
	
	@NotBlank(message = "id is required")
	UUID id, 
	
	String title, 
	
	TaskStatus status, 
	
	PriorityLevel priority, 
	
	String description,
	
	Long expectedConclusionTimestamp, 
	
	UUID creatorId, 
	
	UUID workerId, 
	
	UUID projectId
){}
