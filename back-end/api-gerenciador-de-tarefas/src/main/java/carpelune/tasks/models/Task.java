package carpelune.tasks.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column()
	private String title;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	@Enumerated(EnumType.STRING)
	private PriorityLevel priority;
	
	@Column()
	private String description;
	
	@Column(name = "creation_timestamp")
	private Long creationTimestamp;
	
	@Column(name = "expected_conclusion_timestamp")
	private Long expectedConclusionTimestamp;
	
	@Column(name = "creator_id")
	private UUID creatorId;
	
	@Column(name = "worker_id")
	private UUID workerId;
	
	@Column(name = "project_id")
	private UUID projectId;
	
	
	public Task() {
		
	}
	
	public Task(
		UUID id, String title, TaskStatus status, PriorityLevel priority, String description, UUID workerId,
		Long creationTimestamp, Long expectedConclusionTimestamp, UUID creatorId, UUID projectId
	) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.priority = priority;
		this.description = description;
		this.creationTimestamp = creationTimestamp;
		this.expectedConclusionTimestamp = expectedConclusionTimestamp;
		this.creatorId = creatorId;
		this.workerId = workerId;
		this.projectId = projectId;
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public PriorityLevel getPriority() {
		return priority;
	}

	public void setPriority(PriorityLevel priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Long getExpectedConclusionTimestamp() {
		return expectedConclusionTimestamp;
	}

	public void setExpectedConclusionTimestamp(Long expectedConclusionTimestamp) {
		this.expectedConclusionTimestamp = expectedConclusionTimestamp;
	}

	public UUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(UUID creatorId) {
		this.creatorId = creatorId;
	}

	public UUID getWorkerId() {
		return workerId;
	}

	public void setWorkerId(UUID workerId) {
		this.workerId = workerId;
	}

	public UUID getProjectId() {
		return projectId;
	}

	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationTimestamp, creatorId, description, expectedConclusionTimestamp, id, priority,
				projectId, status, title, workerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(creationTimestamp, other.creationTimestamp) && Objects.equals(creatorId, other.creatorId)
			&& Objects.equals(description, other.description)
			&& Objects.equals(expectedConclusionTimestamp, other.expectedConclusionTimestamp)
			&& Objects.equals(id, other.id) && priority == other.priority
			&& Objects.equals(projectId, other.projectId) && status == other.status
			&& Objects.equals(title, other.title) && Objects.equals(workerId, other.workerId);
	}	
}
