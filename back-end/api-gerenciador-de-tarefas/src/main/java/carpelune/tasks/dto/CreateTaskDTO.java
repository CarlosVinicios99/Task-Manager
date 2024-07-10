package carpelune.tasks.dto;

import java.util.UUID;

import carpelune.tasks.models.PriorityLevel;
import carpelune.tasks.models.TaskStatus;

public record CreateTaskDTO(
	String title, TaskStatus status, PriorityLevel priority, String description, 
	Long expectedConclusionTimestamp, UUID creatorId, UUID workerId, UUID projectId
) {
	
}
